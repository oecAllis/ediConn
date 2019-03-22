package com.oecgroup.parser.edi.spec;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Allis Kuo on 2019-03-20
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EdiRef {

  String ref();

  int conNotUsedCount() default 0;
}
