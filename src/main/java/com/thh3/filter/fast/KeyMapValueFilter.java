package com.thh3.filter.fast;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.serializer.ValueFilter;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class KeyMapValueFilter implements ValueFilter {
    private String key;
    private MarkFilter markFilter;
    @Override
    public Object process(Object object, String propertyName, Object propertyValue) {
        if (propertyValue == null || StrUtil.isBlank(String.valueOf(propertyValue))) {
            return propertyValue;
        }
        if (propertyName.equals(key) && object instanceof Map) {
            return markFilter.doMask(propertyValue);
        }
        return propertyValue;
    }
}
