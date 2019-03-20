package com.oecgroup.parser.edi.spec;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Allis Kuo on 2019-03-20
 * To transmit identifying numbers, dates, and other basic data relating to the
 * transaction set
 * B4***VD*20160526*2245**HASU*431617*L*45G1*JACKSONVILLE, FL,
 * US*CI*0~
 */
public class B4 {

  //Not used B401
  private String code;
  //Not used B402
  private int number;
  //ID1-2 B403
  private String statusCode;
  //DT8 B404 format yyyymmdd
  private LocalDate date;
  //TM4 B405 format hhmm
  private LocalDateTime time;
  //Not used B406
  private String location;
  //An1-4 B407
  private String equipInitial;
  //An1-10 B408
  private String equipNumber;
  //ID1-2 E empty L load B409
  private String equipStatusCode;
  //ID4 B410
  private String equipType;
  //AN1-30 UNLOCODE or City name
  private String locationCode;
  //ID1-2 CI city UN UNLOCODE
  private String locationQual;
  //N1
  private int checkDigit;

}
