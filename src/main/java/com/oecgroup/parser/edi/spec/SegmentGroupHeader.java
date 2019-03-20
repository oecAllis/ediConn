package com.oecgroup.parser.edi.spec;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Allis Kuo on 2019-03-20
 * Marks the beginning of the functional group and provides the sender/receiver identification.
 * GS*QO*HAMSUD*RECEIVER ID*20160526*2245*1000*X*004010~
 *  <group GroupType="PO" ApplSender="4405197800" ApplReceiver="999999999" Date="20101127"
 *             Time="1719" Control="1421" StandardCode="X" StandardVersion="004010VICS">
 */
public class SegmentGroupHeader {

  //ID2 GS01 QO Ocean Shipment Status Information
  private String groupType;
  //AN2-15 GS02
  private String applSender;
  //AN2-15 GS03
  private String applReceiver;
  //DT8 GS04 format yyyymmdd
  private LocalDate date;
  //TM4-8 GS05 format hhmmss
  private LocalDateTime time;
  //N1-9 GS06
  private String control;
  //ID1-2 X ANSI X-12 GS07
  private String standardCode;
  //AN1-12 003060 version 3 release 6
  private String standardVersion;
}
