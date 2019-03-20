package com.oecgroup.parser.edi.spec;

/**
 * Created by Allis Kuo on 2019-03-20
 * To indicate the start of a transaction set and to assign a control number
 * ST*315*0001~
 */
public class TransactionHeader {

  //ID3 ST01 315 Ocean
  private String transactionId;
  //AN4-9 ST02
  private String transactionControl;
}
