package com.thh3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.thh3.filter.jack.AnyBeanPropertyFilter;

public class MaskJackUtil {

    public static String toJSONString(Object object) throws JsonProcessingException {
        return writeValueAsString(object);
    }
    public static String writeValueAsString(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        FilterProvider filters = new SimpleFilterProvider().addFilter("MaskAny", new AnyBeanPropertyFilter());
        mapper.setFilterProvider(filters);
        return mapper.writeValueAsString(object);
    }
}
