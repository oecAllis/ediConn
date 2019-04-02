package com.oecgroup.parser.text;

import com.ancientprogramming.fixedformat4j.annotation.Align;
import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.FixedFormatPattern;
import com.ancientprogramming.fixedformat4j.annotation.Record;
import java.util.Date;

/**
 * Created by Allis Kuo on 2019-03-19
 */
@Record
public class BasicRecord {

  private String MblNo;
  private String EquipmentInitial;
  private String EquipmentNumber;
  private String EquipmentCheckDigit;
  private String EventTypeCode;
  private Date EventDate;
  private String CurrentLocationUNLCODE;
  private String SightingCity;
  private String SightingState;
  private String SCAC;
  private String ETACentury;
  private Date RetaEventDate;
  private String DestinationLocationUNLCODE;
  private String DestinationCity;
  private String DestinationState;
  private String ReportingRailroadSCAC;

  // 2
  @Field(offset = 2, length = 4)
  public String getEquipmentInitial() {
    return EquipmentInitial;
  }

  public void setEquipmentInitial(String EquipmentInitial) {
    this.EquipmentInitial = EquipmentInitial;
  }

  // 3
  @Field(offset = 6, length = 6)
  public String getEquipmentNumber() {
    return EquipmentNumber;
  }

  public void setEquipmentNumber(String EquipmentNumber) {
    this.EquipmentNumber = EquipmentNumber;
  }

  // 4 ~ 9
  @Field(offset = 12, length = 12)
  @FixedFormatPattern("yyyyMMddHHmm")
  public Date getEventDate() {
    return EventDate;
  }

  public void setEventDate(Date EventDate) {
    this.EventDate = EventDate;
  }

  // 12
  @Field(offset = 27, length = 9, align = Align.LEFT)
  public String getSightingCity() {
    return SightingCity;
  }

  public void setSightingCity(String SightingCity) {
    this.SightingCity = SightingCity;
  }

  // 13
  @Field(offset = 36, length = 2)
  public String getSightingState() {
    return SightingState;
  }

  public void setSightingState(String SightingState) {
    this.SightingState = SightingState;
  }

  // 23
  @Field(offset = 68, length = 4)
  public String getReportingRailroadSCAC() {
    return ReportingRailroadSCAC;
  }

  public void setReportingRailroadSCAC(String ReportingRailroadSCAC) {
    this.ReportingRailroadSCAC = ReportingRailroadSCAC;
  }

  // 26
  @Field(offset = 74, length = 9)
  public String getDestinationCity() {
    return DestinationCity;
  }

  public void setDestinationCity(String DestinationCity) {
    this.DestinationCity = DestinationCity;
  }

  // 27
  @Field(offset = 83, length = 2)
  public String getDestinationState() {
    return DestinationState;
  }

  public void setDestinationState(String DestinationState) {
    this.DestinationState = DestinationState;
  }

  // 34
  @Field(offset = 117, length = 2)
  public String getETACentury() {
    return ETACentury;
  }

  public void setETACentury(String ETACentury) {
    this.ETACentury = ETACentury;
  }

  // 34 ~ 38
  @Field(offset = 117, length = 10)
  @FixedFormatPattern("yyyyMMddHH")
  public Date getRetaEventDate() {
    return RetaEventDate;
  }

  public void setRetaEventDate(Date RetaEventDate) {
    this.RetaEventDate = RetaEventDate;
  }

  // 39
  @Field(offset = 127, length = 4)
  public String getEventTypeCode() {
    return EventTypeCode;
  }

  public void setEventTypeCode(String EventTypeCode) {
    this.EventTypeCode = EventTypeCode;
  }

  // 41
  @Field(offset = 132, length = 5)
  public String getCurrentLocationUNLCODE() {
    return CurrentLocationUNLCODE;
  }

  public void setCurrentLocationUNLCODE(String CurrentLocationUNLCODE) {
    this.CurrentLocationUNLCODE = CurrentLocationUNLCODE;
  }

  // 43
  @Field(offset = 138, length = 5)
  public String getDestinationLocationUNLCODE() {
    return DestinationLocationUNLCODE;
  }

  public void setDestinationLocationUNLCODE(String DestinationLocationUNLCODE) {
    this.DestinationLocationUNLCODE = DestinationLocationUNLCODE;
  }

  // 45
  @Field(offset = 144, length = 1)
  public String getEquipmentCheckDigit() {
    return EquipmentCheckDigit;
  }

  public void setEquipmentCheckDigit(String EquipmentCheckDigit) {
    this.EquipmentCheckDigit = EquipmentCheckDigit;
  }

  // 47
  @Field(offset = 146, length = 20, align = Align.LEFT)
  public String getMblNo() {
    return MblNo;
  }

  public void setMblNo(String MblNo) {
    this.MblNo = MblNo;
  }

  // 49
  @Field(offset = 167, length = 4)
  public String getSCAC() {
    return SCAC;
  }

  public void setSCAC(String SCAC) {
    this.SCAC = SCAC;
  }

  // 2 + 3 +45
  public String getCntrNo() {
    return getEquipmentInitial() + getEquipmentNumber() + getEquipmentCheckDigit();
  }

  // 12 + 13
  public String getEventLocName() {
    return getSightingCity() + "," + getSightingState();
  }

  // 26 + 27
  public String getRetaEventLocName() {
    return getDestinationCity() + "," + getDestinationState();
  }
}
