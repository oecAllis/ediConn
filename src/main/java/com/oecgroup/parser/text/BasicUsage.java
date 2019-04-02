package com.oecgroup.parser.text;

import com.ancientprogramming.fixedformat4j.format.FixedFormatManager;
import com.ancientprogramming.fixedformat4j.format.impl.FixedFormatManagerImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Allis Kuo on 2019-03-19
 */
public class BasicUsage {

  private static FixedFormatManager manager = new FixedFormatManagerImpl();
  private static String txtPath = "D://Project/Java/ediConn/tmp/OECGROUP_CLM.E9F601C1.20190329023216313";

  public static void main(String[] args) {

    File txtFile = new File(txtPath);
    if(txtFile.exists()){
      BufferedReader reader = null;
      try {
        reader = new BufferedReader(new FileReader(txtFile));
        String tempString = null;

        while ((tempString = reader.readLine()) != null) {
          if(tempString.length() >= 170){
            BasicRecord record = manager.load(BasicRecord.class, tempString);

            System.out.println("---------------------------------------------------------------------");
            System.out.println("The MBL NO: " + record.getMblNo());
            System.out.println("The CNTR NO: " + record.getCntrNo());
            System.out.println("The Event Type Code: " + record.getEventTypeCode());
            System.out.println("The Event Date: " + record.getEventDate());
            System.out.println("The Event Log: " + record.getCurrentLocationUNLCODE());
            System.out.println("The Event Loc Name: " + record.getEventLocName());
            System.out.println("The SCAC: " + record.getSCAC());
            System.out.println("The File Name: " + txtFile.getName());
            System.out.println("The Prtnr Event CD: " + record.getEventTypeCode());
            System.out.println("The Reta Event Date: " + record.getRetaEventDate());
            System.out.println("The Reta Event Log: " + record.getDestinationLocationUNLCODE());
            System.out.println("The Reta Event Loc Name: " + record.getRetaEventLocName());
            System.out.println("The RailRoad SCAC: " + record.getReportingRailroadSCAC());
            System.out.println("Exported: " + manager.export(record));
            System.out.println("---------------------------------------------------------------------");
          }
        }
        reader.close();
      }
      catch (IOException e) {
        e.printStackTrace();
      }
      finally {
        if (reader != null) {
          try {
            reader.close();
          } catch (IOException e1) {
          }
        }
      }
//      String string = "string    001232008-05-29";
//      BasicRecord record = manager.load(BasicRecord.class, string);
//
//      System.out.println("The parsed string: " + record.getStringData());
//      System.out.println("The parsed integer: " + record.getIntegerData());
//      System.out.println("The parsed date: " + record.getDateData());
//
//      record.setIntegerData(100);
//      System.out.println("Exported: " + manager.export(record));
    }
    else{
      System.out.println("File Not Find");
    }
  }
}
