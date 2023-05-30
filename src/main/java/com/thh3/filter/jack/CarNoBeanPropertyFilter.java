package com.thh3.filter.jack;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.thh3.annotation.MaskCarNo;
import com.thh3.filter.AllPattern;
import com.thh3.filter.fast.MarkFilter;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarNoBeanPropertyFilter extends SimpleBeanPropertyFilter implements MarkFilter {

    final Pattern pattern = AllPattern.CAR_NO_PATTERN;

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
            if (field.isAnnotationPresent(MaskCarNo.class)) {
                String result = doMask(fieldValue);
                if (result != null) {
                    field.set(pojo, result);
                }
            }
        } catch (NoSuchFieldException e) {
            // throw new RuntimeException(e);
        }
    }

    @Override
    public String doMask(Object propertyValue) {
        Matcher matcher = pattern.matcher(String.valueOf(propertyValue));
        if (matcher.find()) {
            String g1 = matcher.group(1);
            String g2 = matcher.group(2);
            String g3 = matcher.group(3);
            StringBuilder sb = new StringBuilder(10);
            sb.append(g1);
            sb.append(g2.replaceAll(".","*"));
            sb.append(g3);
            return sb.toString();
        }
        return null;
    }
}
