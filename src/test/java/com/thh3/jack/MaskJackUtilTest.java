package com.thh3.jack;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thh3.AnyDTO;
import com.thh3.MaskJackUtil;
import org.junit.Test;

public class MaskJackUtilTest {

    @Test
    public void test() throws JsonProcessingException {
        AnyDTO dto = new AnyDTO("粤B-D23456", "13700137000");
        String json = MaskJackUtil.writeValueAsString(dto);
        System.out.println(json);
    }
}
