package com.oecgroup.parser.text;

import com.ancientprogramming.fixedformat4j.format.FixedFormatManager;
import com.ancientprogramming.fixedformat4j.format.impl.FixedFormatManagerImpl;

/**
 * Created by Allis Kuo on 2019-03-19
 */
public class BasicUsage {

  private static FixedFormatManager manager = new FixedFormatManagerImpl();

  public static void main(String[] args) {
    String string = "string    001232008-05-29";
    BasicRecord record = manager.load(BasicRecord.class, string);

    System.out.println("The parsed string: " + record.getStringData());
    System.out.println("The parsed integer: " + record.getIntegerData());
    System.out.println("The parsed date: " + record.getDateData());

    record.setIntegerData(100);
    System.out.println("Exported: " + manager.export(record));
  }
}
