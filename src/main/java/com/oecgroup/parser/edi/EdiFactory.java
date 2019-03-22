package com.oecgroup.parser.edi;

import com.oecgroup.parser.edi.loops.EdiStructure;
import com.oecgroup.parser.edi.spec.X12_315;
import com.oecgroup.parser.edi.spec.X12_315.X12_315_txn;
import com.oecgroup.parser.edi.token.EDITokenizer;
import com.oecgroup.parser.edi.token.Element;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBException;
import org.rapidoid.io.IO;
import org.xml.sax.SAXException;

/**
 * Created by Allis Kuo on 2019-03-21
 */
public class EdiFactory {

  public static EdiReader createEdiReader(InputStream inStream) throws IOException {
    // parse from inStream to know which edi type
    EDITokenizer ediTokenizer = new EDITokenizer(
        new InputStreamReader(inStream, Charset.defaultCharset()));
    final List<List<Element>> headers = new ArrayList<>(3);
    ediTokenizer.next(); //ISA
    headers.add(ediTokenizer.next()); // GS
    headers.add(ediTokenizer.next()); // ST
    headers.add(ediTokenizer.next()); // BHT
    // till here should know which spec to refer
    try {
      EdiStructure structure = EdiStructure.fromStream(IO.resourceAsStream("META-INF/315loop.xml"));
      return new EdiReader("edi315", structure);
    } catch (JAXBException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static void main(String[] args) {
    String _raw315 =
        "ISA*00*          *00*          *ZZ*CMACGM         *ZZ*OECGROUP       *181012*0524*U*00401*000081466*0*P*>~\n"
            + "GS*QO*CMACGM*OECGROUP*20181012*0524*81466*X*004010~\n"
            + "ST*315*0001~\n"
            + "B4***VD*20181012*1242*HKHKG*CMAU*512749*L*4500*HKHKG*UN*8~\n"
            + "N9*BM*CMDUTHD0479278~\n"
            + "N9*BN*THD0479278~\n"
            + "N9*SN*G2049377~\n"
            + "N9*EQ*CMAU5127498~\n"
            + "Q2*9450612*****400***0PG23E1MA***L*CMA CGM LA SCALA~\n"
            + "R4*5*UN*HKHKG*COSCO HIT TERMINAL LTD*HK~\n"
            + "DTM*140*20181012*1242*LT~\n"
            + "R4*L*UN*THLCH*LAEM CHABANG*TH***20~\n"
            + "DTM*140*20180922*1200~\n"
            + "R4*D*UN*USHOU*HOUSTON, TX*US***TX~\n"
            + "DTM*139*20181110*1200~\n"
            + "R4*R*UN*THLCH*LAEM CHABANG*TH***20~\n"
            + "DTM*140*20180922*1200~\n"
            + "R4*E*UN*USHOU*HOUSTON, TX*US***TX~\n"
            + "DTM*139*20181110*1200~\n"
            + "SE*18*0001~\n"
            + "ST*315*0002~\n"
            + "B4***AE*20181012*1242*HKHKG*CMAU*512749*L*4500*HKHKG*UN*8~\n"
            + "N9*BM*CMDUTHD0479278~\n"
            + "N9*BN*THD0479278~\n"
            + "N9*SN*G2049377~\n"
            + "N9*EQ*CMAU5127498~\n"
            + "Q2*9450612*****400***0PG23E1MA***L*CMA CGM LA SCALA~\n"
            + "R4*5*UN*HKHKG*COSCO HIT TERMINAL LTD*HK~\n"
            + "DTM*140*20181012*1242*LT~\n"
            + "R4*L*UN*THLCH*LAEM CHABANG*TH***20~\n"
            + "DTM*140*20180922*1200~\n"
            + "R4*D*UN*USHOU*HOUSTON, TX*US***TX~\n"
            + "DTM*139*20181110*1200~\n"
            + "R4*R*UN*THLCH*LAEM CHABANG*TH***20~\n"
            + "DTM*140*20180922*1200~\n"
            + "R4*E*UN*USHOU*HOUSTON, TX*US***TX~\n"
            + "DTM*139*20181110*1200~\n"
            + "SE*18*0002~"
            + "GE*2*81466~\n"
            + "IEA*1*000081466~";
    try {
      EdiReader reader = createEdiReader(new ByteArrayInputStream(_raw315.getBytes()));
      reader
          .parse(new InputStreamReader(new ByteArrayInputStream(_raw315.getBytes())), new X12_315(),
              new X12_315_txn());
    } catch (IOException | SAXException e) {
      e.printStackTrace();
    }
  }

}
