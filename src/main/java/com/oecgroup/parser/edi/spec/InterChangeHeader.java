package com.oecgroup.parser.edi.spec;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Allis Kuo on 2019-03-20
 * Marks the beginning of the transmission and provides the sender/receiver identification.
 * ISA*00* *00* *01*054318936 *01*123456789 *020801*0900*U*00400*00000012* 0* T*>
 * <interchange Standard="ANSI X.12" AuthorizationQual="01" Authorization="0000000000"
 *         SecurityQual="01" Security="0000000000" Date="101127" Time="1719" StandardsId="U"
 *         Version="00400" Control="000003438" AckRequest="0" TestIndicator="P">
 *         <sender>
 *             <address Id="ABCDEFGHIJKLMNO" Qual="ZZ"/>
 *         </sender>
 *         <receiver>
 *             <address Id="123456789012345" Qual="ZZ"/>
 *         </receiver>
 */
public class InterChangeHeader {
  // ID2, 00 not used ISA01
  private String authorizationQual;
  // AN10 ISA02
  private String authorization;
  // ID2, 00 not used ISA03
  private String securityQual;
  // AN10 ISA04
  private String security;
  // ID2 01 Duns, ZZ - mutually defined ISA05
  private String senderQual;
  // AN15 ISA06
  private String senderId;
  // ID02 ISA07  same as ISA05
  private String receiverQual;
  // AN15 ISA08
  private String receiverId;
  // DT6 ISA09 format: yymmdd
  private LocalDate date;
  // TM4 ISA10 format hhmm
  private LocalDateTime time;
  // ID1 U US EDI community ISA11
  private String standardsId;
  // ID5 00200 -1982 00300 - 1992 00400 - 1997 ISA12
  private String version;
  // N09 ISA13
  private String control;
  // ID1 0 no ack req 1 ack req ISA14
  private String ackRequest;
  // ID1 I information T test P production ISA15
  private String testIndicator;
  // AN1 ISA16 hidden
  private String segmentDelimiter;
}
