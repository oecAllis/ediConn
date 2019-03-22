package com.oecgroup.integration.model;

import java.time.LocalDateTime;

/**
 * Created by Allis Kuo on 2019-03-22
 */
public class OceanEvent {

  private String mbl_no;
  private String cntr_no;
  private String vessel;
  private String voyage;
  private String event_code; // enum
  private LocalDateTime event_date;
  private String event_type; // enum
  private String event_loc;
  private String event_loc_name;
  private String event_desc;
  private long seq;
  private String data_source; // enum
  private LocalDateTime create_date;
  private String scac;
  private String send_flag;
  private String itrack_mbl_no;
  private String id;
  private String filename;
  private LocalDateTime update_date;
  private String update_user;
  private String status; // enum
  private String prtnr_event_cd; // what is this?
  private String pickup_no;
  private String last_free_date;
  private String it_no;
  private String id_map_flag; // enum
  private String hbl_match_flag;
  private String orab2b_msg_id;
  private String orasoa_proc_id; // what is this?
  private String edi_isa_ctrl_no;
  private String edi_st_trx_no;
  private String bookno_flag;
  private String vessel_cd;
  private String reta_event_date;
  private String reta_event_loc;
  private String reta_event_loc_name;
  private String railroad_scac;
  private LocalDateTime utc_update_date;
  private String bkg_no;
  private String vgm_date; // why nvarchar???
}
