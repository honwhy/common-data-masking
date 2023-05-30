package com.thh3.jack;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.thh3.MobileDTO;
import com.thh3.filter.jack.MobileBeanPropertyFilter;
import org.junit.Assert;
import org.junit.Test;

public class MaskJackMobileTest {

    @Test
    public void test() throws JsonProcessingException {
        MobileDTO dto = new MobileDTO("13700137000");
        ObjectMapper mapper = new ObjectMapper();
        FilterProvider filters = new SimpleFilterProvider().addFilter("MaskAny", new MobileBeanPropertyFilter());
        mapper.setFilterProvider(filters);
        String json = mapper.writeValueAsString(dto);
        MobileDTO dto2 = new MobileDTO("137****7000");
        String json2 = JSON.toJSONString(dto2);
        Assert.assertEquals(json, json2);
    }
}
