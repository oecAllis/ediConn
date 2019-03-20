package com.oecgroup.parser.edi.spec;

/**
 * Created by Allis Kuo on 2019-03-20
 * Loop 060
 * Contractual or operational port or point relevant to the movement of the cargo
 * R4*L*UN*DEHAM*Hamburg*DE~
 * R4*I*CI*N/A*CSX NORTHWEST OHIO RAMP~
 * R4 code seq {5, L, D, R, E}
 */
public class R4 {
  //ID1 R401 5 Activity Location D Port of Discharge E Place of Delivery I Interim Point
  //L Port of Loading R Place of Receipt T transhipment Port
  private String funcCode;
  //ID1-2 R402 CI UN
  private String locQual;
  //AN1-30 R403 if R402==CI, then "N/A"
  private String locId;
  //An2-24 R404
  private String portName;
  //ID2-3 R405
  private String countryCode;
  //An2-30 R406
  private String terminal;
  //An1-4 R407
  private String pierNum;
  //ID2
  private String stateCode;
}
