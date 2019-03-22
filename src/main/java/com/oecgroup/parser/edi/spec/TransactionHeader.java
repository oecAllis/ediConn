package com.oecgroup.parser.edi.spec;

/**
 * Created by Allis Kuo on 2019-03-20 To indicate the start of a transaction set and to assign a
 * control number ST*315*0001~
 */
public class TransactionHeader implements Segment {

  //ID3 ST01 315 Ocean
  @EdiRef(ref = "ST01")
  public String transactionId;
  //AN4-9 ST02
  @EdiRef(ref = "ST02")
  public String transactionControl;
}
