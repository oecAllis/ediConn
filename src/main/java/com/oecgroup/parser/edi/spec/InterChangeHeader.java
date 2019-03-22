package com.oecgroup.parser.edi.spec;

/**
 * Created by Allis Kuo on 2019-03-20 Marks the beginning of the transmission and provides the
 * sender/receiver identification. ISA*00* *00* *01*054318936 *01*123456789
 * *020801*0900*U*00400*00000012* 0* T*> <interchange Standard="ANSI X.12" AuthorizationQual="01"
 * Authorization="0000000000" SecurityQual="01" Security="0000000000" Date="101127" Time="1719"
 * StandardsId="U" Version="00400" Control="000003438" AckRequest="0" TestIndicator="P">
 * <sender>
 * <address Id="ABCDEFGHIJKLMNO" Qual="ZZ"/>
 * </sender>
 * <receiver>
 * <address Id="123456789012345" Qual="ZZ"/>
 * </receiver>
 */
public class InterChangeHeader implements Segment {

  // ID2, 00 not used ISA01
  @EdiRef(ref = "ISA01")
  public String authorizationQual;
  // AN10 ISA02
  @EdiRef(ref = "ISA02")
  public String authorization;
  // ID2, 00 not used ISA03
  @EdiRef(ref = "ISA03")
  public String securityQual;
  // AN10 ISA04
  @EdiRef(ref = "ISA04")
  public String security;
  // ID2 01 Duns, ZZ - mutually defined ISA05
  @EdiRef(ref = "ISA05")
  public String senderQual;
  // AN15 ISA06
  @EdiRef(ref = "ISA06")
  public String senderId;
  // ID02 ISA07  same as ISA05
  @EdiRef(ref = "ISA07")
  public String receiverQual;
  // AN15 ISA08
  @EdiRef(ref = "ISA08")
  public String receiverId;
  // DT6 ISA09 format: yymmdd
  @EdiRef(ref = "ISA09")
  public String date;
  // TM4 ISA10 format hhmm
  @EdiRef(ref = "ISA10")
  public String time;
  // ID1 U US EDI community ISA11
  @EdiRef(ref = "ISA11")
  public String standardsId;
  // ID5 00200 -1982 00300 - 1992 00400 - 1997 ISA12
  @EdiRef(ref = "ISA12")
  public String version;
  // N09 ISA13
  @EdiRef(ref = "ISA13")
  public String control;
  // ID1 0 no ack req 1 ack req ISA14
  @EdiRef(ref = "ISA14")
  public String ackRequest;
  // ID1 I information T test P production ISA15
  @EdiRef(ref = "ISA15")
  public String testIndicator;
  // AN1 ISA16 hidden
  @EdiRef(ref = "ISA16")
  public String segmentDelimiter;
}
