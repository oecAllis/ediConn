package com.oecgroup.integration.model;

import java.time.LocalDateTime;

/**
 * Created by Allis Kuo on 2019-03-22
 */
public class EventLocMap {

  private String scac_cd;
  private String prtnr_event_cd;
  private String date_type;
  private String recpt_locate; // should be boolean...
  private String load_port;
  private String discharge_port;
  private String deliver_locate;
  private String tranship_port;
  private String event_desc;
  private String oec_event_cd;
  private String em2_event_cd;
  private LocalDateTime src_update_dt;
  private LocalDateTime ods_update_dt;
}
