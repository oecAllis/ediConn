package com.oecgroup.parser.edi.spec;

/**
 * Created by Allis Kuo on 2019-03-20 Loop R4 070 To specify pertinent dates and times
 * DTM*139*20160526*224529
 */
public class DTM implements Segment {

  //ID3 DTM01 139 Estimated 140 Actual
  @EdiRef(ref = "DTM01")
  public String dtQual;
  //DT8 DTM02 format yyyymmdd
  @EdiRef(ref = "DTM02")
  public String date;
  //TM4-8 format hhmmss
  @EdiRef(ref = "DTM03")
  public String time;
  //ID2 DTM04
  @EdiRef(ref = "DTM04")
  public String zone;
  //not used after
}
