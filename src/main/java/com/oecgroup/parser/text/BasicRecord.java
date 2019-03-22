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

  private String stringData;
  private Integer integerData;
  private Date dateData;


  @Field(offset = 1, length = 10)
  public String getStringData() {
    return stringData;
  }

  public void setStringData(String stringData) {
    this.stringData = stringData;
  }

  @Field(offset = 11, length = 5, align = Align.RIGHT, paddingChar = '0')
  public Integer getIntegerData() {
    return integerData;
  }

  public void setIntegerData(Integer integerData) {
    this.integerData = integerData;
  }

  @Field(offset = 16, length = 10)
  @FixedFormatPattern("yyyy-MM-dd")
  public Date getDateData() {
    return dateData;
  }

  public void setDateData(Date dateData) {
    this.dateData = dateData;
  }
}
