package com.thh3.jack;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.thh3.CarNoDTO;
import com.thh3.filter.jack.CarNoBeanPropertyFilter;
import org.junit.Assert;
import org.junit.Test;

public class MaskJackCarNoTest {

    @Test
    public void test() throws JsonProcessingException {
        CarNoDTO dto = new CarNoDTO("粤B-D23456");
        ObjectMapper mapper = new ObjectMapper();
        FilterProvider filters = new SimpleFilterProvider().addFilter("MaskAny", new CarNoBeanPropertyFilter());
        mapper.setFilterProvider(filters);
        String json = mapper.writeValueAsString(dto);
        CarNoDTO dto2 = new CarNoDTO("粤B-*****6");
        String json2 = JSON.toJSONString(dto2);
        Assert.assertEquals(json, json2);
    }
}
