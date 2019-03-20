package com.oecgroup.parser.edi.spec;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Allis Kuo on 2019-03-20
 * Loop R4 070
 * To specify pertinent dates and times
 * DTM*139*20160526*224529
 */
public class DTM {
  //ID3 DTM01 139 Estimated 140 Actual
  private String dtQual;
  //DT8 DTM02 format yyyymmdd
  private LocalDate date;
  //TM4-8 format hhmmss
  private LocalDateTime time;
  //ID2 DTM04
  private String zone;
  //not used after
}
