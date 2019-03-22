package com.oecgroup.parser.edi.spec;

import java.util.List;

/**
 * Created by Allis Kuo on 2019-03-21
 */
public class X12 {

  public InterChangeHeader isa;
  public SegmentGroupHeader gs;
  public List<X12_Txn> txns;
  public SegmentGroupTrailer ge;
  public InterChangeTrailer iea;
}
