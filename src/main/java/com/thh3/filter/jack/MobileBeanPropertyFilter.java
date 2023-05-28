package com.thh3.filter.jack;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.thh3.annotation.MaskMobile;

import java.lang.reflect.Field;

public class MobileBeanPropertyFilter extends SimpleBeanPropertyFilter {

    @Override
    public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer) throws Exception {
        try {
            filterField(pojo, jgen, provider, writer);
        } catch (NoSuchFieldException e) {
            // throw new RuntimeException(e);
        }
        super.serializeAsField(pojo, jgen, provider, writer);
    }

    public void filterField(Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer) throws Exception {
        try {
            String propertyName = writer.getName();
            Field field = pojo.getClass().getDeclaredField(propertyName);
            if (field.isAnnotationPresent(MaskMobile.class)) {
                MaskMobile annotation = field.getAnnotation(MaskMobile.class);
                String regex = annotation.regex();
                String replace = annotation.replace();
                field.setAccessible(true);
                Object fieldValue = field.get(pojo);
                String propertyValue = String.valueOf(fieldValue);
                String val = String.valueOf(propertyValue);
                val = val.replaceAll(regex, replace);
                field.set(pojo, val);
            }
        } catch (NoSuchFieldException e) {
            // throw new RuntimeException(e);
        }
    }
}
