package com.thh3.filter.jack;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.thh3.annotation.MaskTag;
import com.thh3.annotation.MaskType;

import java.lang.reflect.Field;

public class AnyBeanPropertyFilter  extends SimpleBeanPropertyFilter {
    private static final CarNoBeanPropertyFilter carNoBeanPropertyFilter = new CarNoBeanPropertyFilter();
    private static final MobileBeanPropertyFilter mobileBeanPropertyFilter = new MobileBeanPropertyFilter();
    @Override
    public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer) throws Exception {
        String propertyName = writer.getName();
        Field field = pojo.getClass().getDeclaredField(propertyName);
        if (field.isAnnotationPresent(MaskTag.class)) {
            MaskTag annotation = field.getAnnotation(MaskTag.class);
            MaskType type = annotation.type();
            switch (type) {
                case ANY:
                    break;
                case CAR_NO:
                    carNoBeanPropertyFilter.filterField(pojo, jgen, provider, writer);
                    break;
                case MOBILE:
                    mobileBeanPropertyFilter.filterField(pojo, jgen, provider, writer);
                    break;
            }
        }
        super.serializeAsField(pojo, jgen, provider, writer);
    }
}
