package com.thh3.fast;

import com.alibaba.fastjson2.JSON;
import com.thh3.CarNoDTO;
import com.thh3.filter.fast.CarNoValueFilter;
import org.junit.Test;

public class MaskCarNoTest {

    @Test
    public void test() {
        CarNoDTO dto = new CarNoDTO("粤B-D23456");
        String json = JSON.toJSONString(dto, new CarNoValueFilter());
        System.out.println(json);
    }

}
