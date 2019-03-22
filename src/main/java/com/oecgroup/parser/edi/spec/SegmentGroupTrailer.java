package com.oecgroup.parser.edi.spec;

/**
 * Created by Allis Kuo on 2019-03-20 Marks the ending of the functional group and provides the
 * sender/ receiver identification GE* 1* 12
 */
public class SegmentGroupTrailer implements Segment {

  //N1-6 GE01
  @EdiRef(ref = "GE01")
  public String transactionCount;
  //N1-9 GE02 should equal GS06
  @EdiRef(ref = "GE02")
  public String control;
}
