package com.oecgroup.parser.edi.spec;

/**
 * Created by Allis Kuo on 2019-03-20 To transmit identifying information as specified by the
 * Reference Identification Qualifier N9*IB*82564523**20160523*224521*LT~
 */
public class N9 implements Segment {

  //ID2-3 N901 4F carrier assigned BM Bill of Loading# BN Booking# CO Customer Order# CR Custome Reference#
  //EQ Equipment# FN Forwarder Reference#, IB In Bond# MCI Motor Carrier ID# RR Rail Road Carrier Name
  //SCA Stanard Carrier Alpha Code SI Shippers ID# SN Seal# WU Vessel
  @EdiRef(ref = "N901")
  public String refIdQual;
  //An1-30 N902
  @EdiRef(ref = "N902")
  public String refId;
  //An1-45 N903
  @EdiRef(ref = "N903")
  public String desc;
  //DT8 N904 format yyyymmdd
  @EdiRef(ref = "N904")
  public String date;
  //TM4-8 N905format hhmmss
  @EdiRef(ref = "N905")
  public String time;
  //ID2 N906 +-N LT localtime
  @EdiRef(ref = "N906")
  public String zone;
  //not used...
}
