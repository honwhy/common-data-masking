package com.thh3.filter.fast;

import com.alibaba.fastjson.serializer.ValueFilter;
import com.thh3.annotation.MaskAny;
import com.thh3.annotation.MaskCarNo;

import java.lang.reflect.Field;

public class CarNoValueFilter implements ValueFilter {

    @Override
    public Object process(Object object, String propertyName, Object propertyValue) {
        try {
            Field field = object.getClass().getDeclaredField(propertyName);
            if (field.isAnnotationPresent(MaskAny.class)) {
                System.out.println("mask");
            }
            if (field.isAnnotationPresent(MaskCarNo.class)) {
                MaskCarNo annotation = field.getAnnotation(MaskCarNo.class);
                String regex = annotation.regex();
                String replace = annotation.replace();
                String val = String.valueOf(propertyValue);
                val = val.replaceAll(regex, replace);
                return val;
            }
        } catch (NoSuchFieldException e) {
            // throw new RuntimeException(e);
        }
        return propertyValue;
    }
}
