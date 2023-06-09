package com.thh3.filter.jack;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.thh3.annotation.MaskTag;
import com.thh3.annotation.MaskType;
import com.thh3.filter.AllPattern;
import com.thh3.filter.fast.MarkFilter;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

public class MobileBeanPropertyFilter extends SimpleBeanPropertyFilter implements MarkFilter {

    final Pattern pattern = AllPattern.MOBILE_PATTERN;

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
            field.setAccessible(true);
            Object fieldValue = field.get(pojo);
            if (fieldValue == null || StrUtil.isBlank(String.valueOf(fieldValue))) {
                return ;
            }
            if (field.isAnnotationPresent(MaskTag.class)) {
                MaskTag annotation = field.getAnnotation(MaskTag.class);
                if (MaskType.MOBILE.equals(annotation.type())) {
                    String result = doMask(fieldValue);
                    if (result != null) {
                        field.set(pojo, result);
                    }
                }
            }
        } catch (NoSuchFieldException e) {
            // throw new RuntimeException(e);
        }
    }

    @Override
    public String doMask(Object propertyValue) {
        return this.doMask(propertyValue, pattern);
    }
}
