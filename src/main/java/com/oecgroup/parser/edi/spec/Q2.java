package com.oecgroup.parser.edi.spec;

/**
 * Created by Allis Kuo on 2019-03-20 To transmit identifying information relative to identification
 * of vessel, transportation dates, lading quantity, weight, and cube
 * Q2*9449160*DE*******371S***L*CAP SAN MARCO~
 */
public class Q2 implements Segment {

  //ID1-8 Q201
  @EdiRef(ref = "Q201")
  public String vesselCode;
  //ID2-3 Q202
  @EdiRef(ref = "Q202")
  public String countryCode;
  //Q203-208 six elements
  @EdiRef(ref = "Q203", conNotUsedCount = 6)
  public String[] notUsed;
  //An2-10 Q209
  @EdiRef(ref = "Q209")
  public String voyageNumber;
  //Q210-211 two elements
  @EdiRef(ref = "Q210", conNotUsedCount = 2)
  public String[] notUsed1;
  //ID1 L Lloyd's Register of Shipping Q212
  @EdiRef(ref = "Q212")
  public String vesselCodeQual;
  //An2-28 Q213
  @EdiRef(ref = "Q213")
  public String vesselName;
  // not used after
}
