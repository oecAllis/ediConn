package com.oecgroup.parser.edi.spec;

/**
 * Created by Allis Kuo on 2019-03-20 To transmit identifying numbers, dates, and other basic data
 * relating to the transaction set B4***VD*20160526*2245**HASU*431617*L*45G1*JACKSONVILLE, FL,
 * US*CI*0~
 */
public class B4 implements Segment {

  //Not used B401
  @EdiRef(ref = "B401")
  public String code;
  //Not used B402
  @EdiRef(ref = "B402")
  public String number;
  //ID1-2 B403
  @EdiRef(ref = "B403")
  public String statusCode;
  //DT8 B404 format yyyymmdd
  @EdiRef(ref = "B404")
  public String date;
  //TM4 B405 format hhmm
  @EdiRef(ref = "B405")
  public String time;
  //Not used B406
  @EdiRef(ref = "B406")
  public String location;
  //An1-4 B407
  @EdiRef(ref = "B407")
  public String equipInitial;
  //An1-10 B408
  @EdiRef(ref = "B408")
  public String equipNumber;
  //ID1-2 E empty L load B409
  @EdiRef(ref = "B409")
  public String equipStatusCode;
  //ID4 B410
  @EdiRef(ref = "B410")
  public String equipType;
  //AN1-30 UNLOCODE or City name
  @EdiRef(ref = "B411")
  public String locationCode;
  //ID1-2 CI city UN UNLOCODE
  @EdiRef(ref = "B412")
  public String locationQual;
  //N1
  @EdiRef(ref = "B413")
  public String checkDigit;

}
