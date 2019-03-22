package com.oecgroup.parser.edi.spec;

/**
 * Created by Allis Kuo on 2019-03-20 Loop 060 Contractual or operational port or point relevant to
 * the movement of the cargo R4*L*UN*DEHAM*Hamburg*DE~ R4*I*CI*N/A*CSX NORTHWEST OHIO RAMP~ R4 code
 * seq {5, L, D, R, E}
 */
public class R4 implements Segment {

  //ID1 R401 5 Activity Location D Port of Discharge E Place of Delivery I Interim Point
  //L Port of Loading R Place of Receipt T transhipment Port
  @EdiRef(ref = "N401")
  public String funcCode;
  //ID1-2 R402 CI UN
  @EdiRef(ref = "N402")
  public String locQual;
  //AN1-30 R403 if R402==CI, then "N/A"
  @EdiRef(ref = "N403")
  public String locId;
  //An2-24 R404
  @EdiRef(ref = "N404")
  public String portName;
  //ID2-3 R405
  @EdiRef(ref = "N405")
  public String countryCode;
  //An2-30 R406
  @EdiRef(ref = "N406")
  public String terminal;
  //An1-4 R407
  @EdiRef(ref = "N407")
  public String pierNum;
  //ID2
  @EdiRef(ref = "N408")
  public String stateCode;
}
