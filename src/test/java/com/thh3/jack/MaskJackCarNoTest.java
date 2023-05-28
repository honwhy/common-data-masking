package com.thh3.jack;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.thh3.CarNoDTO;
import com.thh3.filter.jack.CarNoBeanPropertyFilter;
import org.junit.Test;

public class MaskJackCarNoTest {

    @Test
    public void test() throws JsonProcessingException {
        CarNoDTO dto = new CarNoDTO("粤B-D23456");
        ObjectMapper mapper = new ObjectMapper();
        FilterProvider filters = new SimpleFilterProvider().addFilter("MaskAny", new CarNoBeanPropertyFilter());
        mapper.setFilterProvider(filters);
        String json = mapper.writeValueAsString(dto);
        System.out.println(json);
    }
}
