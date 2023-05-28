package com.thh3.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@MaskAny
@Retention(RetentionPolicy.RUNTIME)
public @interface MaskMobile {

    String regex() default  "(.{3}).*(.{4})";
    String replace() default "$1****$2";
}
