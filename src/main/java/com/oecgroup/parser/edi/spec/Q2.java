package com.oecgroup.parser.edi.spec;

/**
 * Created by Allis Kuo on 2019-03-20
 * To transmit identifying information relative to identification of vessel,
 * transportation dates, lading quantity, weight, and cube
 * Q2*9449160*DE*******371S***L*CAP SAN MARCO~
 */
public class Q2 {
  //ID1-8 Q201
  private String vesselCode;
  //ID2-3 Q202
  private String countryCode;
  //Q203-208 six elements
  private String[] notUsed;
  //An2-10 Q209
  private String voyageNumber;
  //Q210-211 two elements
  private String[] notused1;
  //ID1 L Lloyd's Register of Shipping Q212
  private String vesselCodeQual;
  //An2-28 Q213
  private String vesselName;
  // not used after
}
