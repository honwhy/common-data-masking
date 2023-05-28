package com.thh3.filter.fast;

import com.alibaba.fastjson.serializer.ValueFilter;
import com.thh3.annotation.MaskMobile;

import java.lang.reflect.Field;

public class MobileValueFilter implements ValueFilter {

    @Override
    public Object process(Object object, String propertyName, Object propertyValue) {
        try {
            Field field = object.getClass().getDeclaredField(propertyName);
            if (field.isAnnotationPresent(MaskMobile.class)) {
                MaskMobile annotation = field.getAnnotation(MaskMobile.class);
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
