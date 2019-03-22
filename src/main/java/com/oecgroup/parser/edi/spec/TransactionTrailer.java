package com.oecgroup.parser.edi.spec;

/**
 * Created by Allis Kuo on 2019-03-20 To indicate the end of the transaction set and provide the
 * count of the transmitted segments (including the beginning (ST) and ending (SE) segments)
 * SE*7*0001~
 */
public class TransactionTrailer implements Segment {

  //N1-10 SE01
  @EdiRef(ref = "SE01")
  public String segmentCounts;
  //AN4-9 SE02
  @EdiRef(ref = "SE02")
  public String transactionControl;
}
