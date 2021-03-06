package com.oecgroup.parser.edi.loops;

import com.oecgroup.parser.edi.token.CompositeElement;
import com.oecgroup.parser.edi.token.Element;
import com.oecgroup.parser.edi.token.RepeatingElement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "startSegment")
public class StartSegment {

  private final static Logger log = LogManager.getLogger(StartSegment.class);
  @XmlAttribute(name = "id", required = true)
  protected String id;
  @XmlElement(name = "qualifier", required = false)
  protected ArrayList<Qualifier> qualifiers;

  public boolean matches(List<Element> segment) {
    boolean soFarSoGood = segment.get(0).toString().equals(id);

    if (qualifiers != null) {
      for (Iterator<Qualifier> i = qualifiers.iterator(); soFarSoGood && i.hasNext(); ) {
        Qualifier q = i.next();
        int position = q.getPosition();
        if (position >= segment.size()) {
          soFarSoGood = false;
        } else {
          Element element = segment.get(position);
          if (element instanceof CompositeElement || element instanceof RepeatingElement) {
            log.info(
                "Composite or repeating elements don't really work well as qualifiers. (segment {} position {})",
                id, position);
            log.debug(
                "Feel free to make a feature request, submit a patch, or just cross your fingers and check something in.");
          }
          if (!q.getValues().contains(element.toString())) {
            soFarSoGood = false;
          }
        }
      }
    }
    return soFarSoGood;
  }

}
