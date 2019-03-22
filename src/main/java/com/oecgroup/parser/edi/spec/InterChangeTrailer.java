package com.oecgroup.parser.edi.spec;

/**
 * Created by Allis Kuo on 2019-03-20 Marks the ending of the functional group and provides the
 * sender/ receiver identification. IEA* 1* 000000012
 */
public class InterChangeTrailer implements Segment {

  //N1-5 IEA01
  @EdiRef(ref = "IEA01")
  public String functionGroupCount;
  //N9 IEA02 should equal ISA13
  @EdiRef(ref = "IEA02")
  public String control;
}
