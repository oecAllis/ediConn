package com.oecgroup.parser.edi.spec;

/**
 * Created by Allis Kuo on 2019-03-20 Marks the beginning of the functional group and provides the
 * sender/receiver identification. GS*QO*HAMSUD*RECEIVER ID*20160526*2245*1000*X*004010~ <group
 * GroupType="PO" ApplSender="4405197800" ApplReceiver="999999999" Date="20101127" Time="1719"
 * Control="1421" StandardCode="X" StandardVersion="004010VICS">
 */
public class SegmentGroupHeader implements Segment {

  //ID2 GS01 QO Ocean Shipment Status Information
  @EdiRef(ref = "GS01")
  public String groupType;
  //AN2-15 GS02
  @EdiRef(ref = "GS02")
  public String applSender;
  //AN2-15 GS03
  @EdiRef(ref = "GS03")
  public String applReceiver;
  //DT8 GS04 format yyyymmdd
  @EdiRef(ref = "GS04")
  public String date;
  //TM4-8 GS05 format hhmmss
  @EdiRef(ref = "GS05")
  public String time;
  //N1-9 GS06
  @EdiRef(ref = "GS06")
  public String control;
  //ID1-2 X ANSI X-12 GS07
  @EdiRef(ref = "GS07")
  public String standardCode;
  //AN1-12 003060 version 3 release 6
  @EdiRef(ref = "GS08")
  public String standardVersion;
}
