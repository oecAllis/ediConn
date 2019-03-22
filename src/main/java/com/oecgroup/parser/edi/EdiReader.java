package com.oecgroup.parser.edi;

import com.oecgroup.parser.edi.loops.EdiStructure;
import com.oecgroup.parser.edi.loops.Loop;
import com.oecgroup.parser.edi.spec.EdiRef;
import com.oecgroup.parser.edi.spec.InterChangeHeader;
import com.oecgroup.parser.edi.spec.InterChangeTrailer;
import com.oecgroup.parser.edi.spec.Segment;
import com.oecgroup.parser.edi.spec.SegmentGroupHeader;
import com.oecgroup.parser.edi.spec.SegmentGroupTrailer;
import com.oecgroup.parser.edi.spec.TransactionHeader;
import com.oecgroup.parser.edi.spec.TransactionTrailer;
import com.oecgroup.parser.edi.spec.X12;
import com.oecgroup.parser.edi.spec.X12_Txn;
import com.oecgroup.parser.edi.token.CompositeElement;
import com.oecgroup.parser.edi.token.EDITokenizer;
import com.oecgroup.parser.edi.token.Element;
import com.oecgroup.parser.edi.token.RepeatingElement;
import com.rits.cloning.Cloner;
import java.io.IOException;
import java.io.Reader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import org.apache.commons.collections4.map.LazyMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rapidoid.u.U;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class EdiReader {

  private final static Logger logger = LogManager.getLogger(EdiReader.class);

  private boolean includeEmptyElements = true;

  private String rootElement;

  private Deque<String> elementStack = new LinkedList<>();

  private Deque<Loop> loopStack = new LinkedList<>();

  private Map<String, BiConsumer<List<Element>, EDITokenizer>> segmentHandlers;

  private Map<String, Set<Integer>> attDefs;

  private X12 x12Instance;

  private X12_Txn txn;

  private int txnCount = 0;

  private int segCount = 0;

  public EdiReader(String rootElement, EdiStructure structure) {
    loopStack.push(structure.getRootLoop());
    this.rootElement = rootElement;
    this.attDefs = structure.getAttributeDefinitions().stream()
        .collect(Collectors.toMap(a -> a.name, a -> a.position));

    BiConsumer<List<Element>, EDITokenizer> startElement = (segment, tokenizer) -> {
      String segId = segment.get(0).toString();

      Set<Integer> attribPositions = this.attDefs.getOrDefault(segId, Collections.emptySet());
      EdiAttributes attributes = new EdiAttributes();
      for (Integer position : attribPositions) {
        attributes.add(String.format("%s%02d", segId, position), segment.get(position).toString());
      }
      startElement(segId, attributes);
    };

    BiConsumer<List<Element>, EDITokenizer> startIsaElement = (segment, tokenizer) -> {
      String segId = segment.get(0).toString();
      startElement(segId, new EdiAttributes("segmentDelimiter", tokenizer.getSegmentDelimiter(),
          "elementDelimiter", tokenizer.getElementDelimiter().toString(),
          "compositeDelimiter", tokenizer.getCompositeDelimiter().toString(),
          "repetitionSeparator", tokenizer.getRepetitionSeparator().toString()));
    };

    BiConsumer<List<Element>, EDITokenizer> createSubElements = (segment, tokenizer) -> {
      String segId = segment.get(0).toString();
      Set<Integer> attribPositions = this.attDefs.getOrDefault(segId, Collections.emptySet());
      Segment segmentInstance = null;
      switch (segId) {
        case "ISA":
          segmentInstance = new InterChangeHeader();
          break;
        case "GS":
          segmentInstance = new SegmentGroupHeader();
          break;
        case "ST":
          if (x12Instance.txns == null) {
            x12Instance.txns = new ArrayList<>();
          }
          segmentInstance = new TransactionHeader();
          break;
        default:
          try {
            Class clazz = Class.forName("com.oecgroup.parser.edi.spec." + segId);
            segmentInstance = (Segment) clazz.getConstructors()[0].newInstance();
          } catch (ClassNotFoundException e) {
            e.printStackTrace();
          } catch (IllegalAccessException e) {
            e.printStackTrace();
          } catch (InstantiationException e) {
            e.printStackTrace();
          } catch (InvocationTargetException e) {
            e.printStackTrace();
          }
      }
      Field[] fields = segmentInstance.getClass().getDeclaredFields();

      try {
        for (int i = 1; i < segment.size(); ++i) {
          if (!attribPositions.contains(i)) {
            String name = String.format("%s%02d", segId, i);
            addElement(name, segment.get(i));
            try {
              renderObject(segmentInstance, name, segment.get(i), fields);
            } catch (IllegalAccessException e) {
              e.printStackTrace();
            }
          }
        }
      } catch (SAXException e) {
        throw new RuntimeException(e);
      }
      switch (segId) {
        case "ISA":
          x12Instance.isa = (InterChangeHeader) segmentInstance;
          break;
        case "GS":
          x12Instance.gs = (SegmentGroupHeader) segmentInstance;
          break;
        case "ST":
          txn.st = (TransactionHeader) segmentInstance;
          segCount++;
          break;
        default:
          try {
            txn.getClass().getDeclaredField(segId.toLowerCase()).set(txn, segmentInstance);
            segCount++;
          } catch (NoSuchFieldException e) {
            //e.printStackTrace();
            try {
              Field f = txn.getClass().getDeclaredField(segId.toLowerCase() + "s"); // meaning list
              if (f.get(txn) == null) {
                f.set(txn, new ArrayList<>());
              }
              ((List) f.get(txn)).add(segmentInstance);
              segCount++;
            } catch (NoSuchFieldException | IllegalAccessException e1) {
              e1.printStackTrace();
            }
          } catch (IllegalAccessException e) {
            e.printStackTrace();
          }
      }
    };

    BiConsumer<List<Element>, EDITokenizer> popToRootLoop = (x, y) -> {
      while (loopStack.size() > 1) {
        endElement();
        loopStack.pop();
      }
    };

    BiConsumer<List<Element>, EDITokenizer> endElement = (segment, tokenizer) -> {
      endElement();
      String segId = segment.get(0).toString();
      Set<Integer> attribPositions = this.attDefs.getOrDefault(segId, Collections.emptySet());
      Segment segmentInstance = null;
      switch (segId) {
        case "SE":
          segmentInstance = new TransactionTrailer();
          break;
        case "GE":
          segmentInstance = new SegmentGroupTrailer();
          break;
        case "IEA":
          segmentInstance = new InterChangeTrailer();
          break;
        default:
          return;
      }
      Field[] fields = segmentInstance.getClass().getDeclaredFields();

      try {
        for (int i = 1; i < segment.size(); ++i) {
          if (!attribPositions.contains(i)) {
            String name = String.format("%s%02d", segId, i);
            addElement(name, segment.get(i));
            try {
              renderObject(segmentInstance, name, segment.get(i), fields);
            } catch (IllegalAccessException e) {
              e.printStackTrace();
            }
          }
        }
      } catch (SAXException e) {
        throw new RuntimeException(e);
      }
      switch (segId) {
        case "IEA":
          x12Instance.iea = (InterChangeTrailer) segmentInstance;
          if (!x12Instance.iea.control.equals(x12Instance.isa.control)) {
            logger.error(
                "control number mismatched: header " + x12Instance.isa.control + " trailer "
                    + x12Instance.iea.control);
          }
          break;
        case "GE":
          x12Instance.ge = (SegmentGroupTrailer) segmentInstance;
          if (Integer.valueOf(x12Instance.ge.transactionCount) != txnCount) {
            logger.error(
                "transaction count mismatched: expected count " + x12Instance.ge.transactionCount
                    + ", real count " + txnCount);
          }
          break;
        case "SE":
          txn.se = (TransactionTrailer) segmentInstance;
          Cloner cloner = new Cloner();
          x12Instance.txns.add(cloner.deepClone(txn));
          segCount++;
          if (Integer.valueOf(txn.se.segmentCounts) != segCount) {
            logger.error(
                "segment count mismatched: expected count " + txn.se.segmentCounts + ", real count "
                    + segCount);
          }
          txnCount++;
          segCount = 0;
          break;
        default:
          break;
      }
    };

    BiConsumer<List<Element>, EDITokenizer> doTransition = (segment, x) -> transitionLevel(segment);

    BiConsumer<List<Element>, EDITokenizer> defaultCase = doTransition.andThen(startElement)
        .andThen(createSubElements).andThen(endElement);

    segmentHandlers = LazyMap.lazyMap(new HashMap<>(), x -> defaultCase);

    String endOfLoops = structure.getLoopsEnd();
    if (U.notEmpty(endOfLoops)) {
      segmentHandlers.put(endOfLoops, popToRootLoop.andThen(defaultCase));
    }
    segmentHandlers.put("ISA", startIsaElement.andThen(createSubElements));
    segmentHandlers.put("GS", startElement.andThen(createSubElements));
    segmentHandlers.put("ST", startElement.andThen(createSubElements));
    segmentHandlers.put("SE", popToRootLoop.andThen(endElement));
    segmentHandlers.put("GE", endElement);
    segmentHandlers.put("IEA", endElement);
  }

  public void parse(Reader reader, X12 x12, X12_Txn txn) throws IOException, SAXException {
    logger.info("Start parsing edi document");
    EDITokenizer tokenizer = new EDITokenizer(reader);
    this.x12Instance = x12;
    this.txn = txn;
    start();
    for (List<Element> segment : tokenizer) {
      String segId = segment.get(0).toString();
      //System.out.println(segId + " : " + segment.size());
      segmentHandlers.get(segId).accept(segment, tokenizer);
    }
    end();
    reader.close();
    logger.info("Finish parsing edi document with total transaction count " + txnCount);
  }

  private void renderObject(Segment segment, String name, Element content, Field[] fields)
      throws IllegalAccessException {
    for (Field f : fields) {
      for (Annotation ano : f.getAnnotations()) {
        if (ano instanceof EdiRef) {
          if (name.equalsIgnoreCase(((EdiRef) ano).ref())) {
            if (!f.getName().startsWith("notUsed")) {
              f.set(segment, content.toString());
              //System.out.println("find element " + content + " in refId " + ((EdiRef) ano).ref());
            }
            break;
          }
        }
      }
    }
  }

  private void start() throws SAXException {
    startElement(rootElement);
  }

  private void end() throws SAXException {
    while (!elementStack.isEmpty()) {
      endElement();
    }
  }

  private void transitionLevel(List<Element> segment) {
    int levelsToPop = 0;
    Iterator<Loop> iterator = loopStack.iterator();
    Loop nextLoop = iterator.next().getTransition(segment);
    while (nextLoop == null && iterator.hasNext()) {
      ++levelsToPop;
      nextLoop = iterator.next().getTransition(segment);
    }

    if (nextLoop != null) {
      for (int i = 0; i < levelsToPop; ++i) {
        endElement();
        loopStack.pop();
      }
      String loopName = nextLoop.getName();
      if (loopName != null) {
        startElement(loopName);
      }
      loopStack.push(nextLoop);
    }
  }

  private void startElement(String elementName) {
    this.startElement(elementName, new AttributesImpl());
  }

  private void startElement(String elementName, Attributes attributes) {
    elementName = elementName.intern();
    elementStack.push(elementName);
    //System.out.println("starting element " + elementName + " attributes size " + attributes.getLength());
  }

  private void endElement() {
    String name = elementStack.pop();
    //System.out.println("ending element " + name);
  }

  private void characters(String string) throws SAXException {
    char[] chars = string.toCharArray();
  }

  private void addElement(String elementName, Element content) throws SAXException {
    if (content instanceof RepeatingElement) {
      addElement(elementName, (RepeatingElement) content);
    } else if (content instanceof CompositeElement) { // the first line > would become compositeElement
      //addElement(elementName, (CompositeElement) content);
    } else {
      this.addElement(elementName, content.toString());
    }
  }

  private void addElement(String elementName, CompositeElement content) throws SAXException {
    Set<Integer> attribPositions = this.attDefs.getOrDefault(elementName, Collections.emptySet());
    EdiAttributes attributes = new EdiAttributes();
    for (Integer position : attribPositions) {
      attributes.add(String.format("C%02d", position), content.getPart(position - 1));
    }

    startElement(elementName, attributes);
    int i = 1;
    for (String s : content.getParts()) {
      int position = i++;
      if (!attribPositions.contains(position)) {
        String name = String.format("C%02d", position);
        this.addElement(name, s);
      }
    }
    endElement();
  }

  private void addElement(String elementName, RepeatingElement content) throws SAXException {
    for (Element element : content.getElements()) {
      addElement(elementName, element);
    }
  }

  private void addElement(String elementName, String content) throws SAXException {
    this.addElement(elementName, new AttributesImpl(), content);
  }

  private void addElement(String elementName, Attributes attributes) throws SAXException {
    startElement(elementName, attributes);
    endElement();
  }

  public void addElement(String elementName, Attributes attributes, String content)
      throws SAXException {
    if (includeEmptyElements || U.notEmpty(content) || attributes.getLength() > 0) {
      startElement(elementName, attributes);
      characters(content);
      endElement();
    }
  }

}