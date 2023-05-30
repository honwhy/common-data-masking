package com.thh3.fast;

import com.alibaba.fastjson2.JSON;
import com.thh3.MobileDTO;
import com.thh3.filter.fast.MobileValueFilter;
import org.junit.Assert;
import org.junit.Test;

public class MaskMobileTest {

    @Test
    public void test() {
        MobileDTO dto = new MobileDTO("13700137000");
        String json = JSON.toJSONString(dto, new MobileValueFilter());
        MobileDTO dto2 = new MobileDTO("137****7000");
        String json2 = JSON.toJSONString(dto2);
        Assert.assertEquals(json, json2);
    }

}
