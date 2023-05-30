package com.thh3.fast;

import com.alibaba.fastjson2.JSON;
import com.thh3.CarNoDTO;
import com.thh3.filter.fast.CarNoValueFilter;
import org.junit.Assert;
import org.junit.Test;

public class MaskCarNoTest {

    @Test
    public void test() {
        CarNoDTO dto = new CarNoDTO("粤B-D23456");
        String json = JSON.toJSONString(dto, new CarNoValueFilter());
        CarNoDTO dto2 = new CarNoDTO("粤B-*****6");
        String json2 = JSON.toJSONString(dto2);
        Assert.assertEquals(json, json2);

    }

}
