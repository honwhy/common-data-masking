package com.thh3.jack;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.thh3.MobileDTO;
import com.thh3.filter.jack.MobileBeanPropertyFilter;
import org.junit.Test;

public class MaskJackMobileTest {

    @Test
    public void test() throws JsonProcessingException {
        MobileDTO dto = new MobileDTO("13700137000");
        ObjectMapper mapper = new ObjectMapper();
        FilterProvider filters = new SimpleFilterProvider().addFilter("MaskAny", new MobileBeanPropertyFilter());
        mapper.setFilterProvider(filters);
        String json = mapper.writeValueAsString(dto);
        System.out.println(json);
    }
}
