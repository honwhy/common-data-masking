package com.thh3.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MaskTag {

    MaskType type() default MaskType.ANY;
}
