package com.oecgroup.conn;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Allis Kuo on 2019-03-20
 * This class is used to know at current time frame, how many edi files need to be handled concurrently
 * Also it needs to keep track of vendor, edi file lists so that once jobs are done properly, remove edi files accordingly
 */
public class EdiFileManager {
  private final static Logger logger = LogManager.getLogger(EdiFileManager.class);
  private LocalDateTime processTime;
  private Map<String, Integer> processCountByVendor;
  private Map<String, List<String>> processFilesByVendor;
  private Map<String, Integer> exceptionCountByVendor;
  private Duration totalConsume;

  public boolean execute() {

    return true;
  }
}
