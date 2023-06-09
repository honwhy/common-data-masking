package com.thh3.filter.fast;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.thh3.annotation.MaskTag;
import com.thh3.annotation.MaskType;
import com.thh3.filter.AllPattern;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

public class MobileValueFilter implements ValueFilter, MarkFilter {

    final Pattern pattern = AllPattern.MOBILE_PATTERN;

    @Override
    public Object process(Object object, String propertyName, Object propertyValue) {
        if (propertyValue == null || StrUtil.isBlank(String.valueOf(propertyValue))) {
            return propertyValue;
        }
        try {
            Field field = object.getClass().getDeclaredField(propertyName);
            if (field.isAnnotationPresent(MaskTag.class)) {
                MaskTag annotation = field.getAnnotation(MaskTag.class);
                MaskType type = annotation.type();
                if (MaskType.MOBILE.equals(type)) {
                    String result = doMask(propertyValue);
                    if (result != null) {
                        return result;
                    }
                }
            }
        } catch (NoSuchFieldException e) {
            // throw new RuntimeException(e);
        }
        return propertyValue;
    }

    @Override
    public String doMask(Object propertyValue) {
        return this.doMask(propertyValue, pattern);
    }
}
