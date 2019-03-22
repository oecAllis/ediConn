package com.oecgroup.parser.edi.spec;

import java.util.List;
import lombok.Data;

/**
 * Created by Allis Kuo on 2019-03-20
 */
@Data
public class X12_315 extends X12 {

  public static class X12_315_txn extends X12_Txn {

    public B4 b4;
    public List<N9> n9s;
    public Q2 q2;
    public List<R4> r4s;
    public List<DTM> dtms;
  }
}
