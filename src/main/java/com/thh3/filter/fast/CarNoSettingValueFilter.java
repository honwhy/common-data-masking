package com.thh3.filter.fast;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.thh3.filter.AllPattern;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarNoSettingValueFilter implements ValueFilter, MarkFilter {
    private Properties properties;
    final Pattern pattern = AllPattern.CAR_NO_PATTERN;

    public CarNoSettingValueFilter(Properties props) {
        this.properties = props;
    }
    @Override
    public Object process(Object object, String propertyName, Object propertyValue) {
        if (propertyValue == null || StrUtil.isBlank(String.valueOf(propertyValue))) {
            return propertyValue;
        }
        String name = object.getClass().getName();
        String key = name + "#" + propertyName;
        if (properties.containsKey(key)) {
            String property = properties.getProperty(key, "");
            if (property.equals("MaskCarNo")) {
                String result = doMask(propertyValue);
                if (result != null) {
                    return result;
                }
            }
        }
        return propertyValue;
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
