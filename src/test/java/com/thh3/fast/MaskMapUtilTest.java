package com.thh3.fast;

import com.alibaba.fastjson.JSON;
import com.thh3.MaskMapUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MaskMapUtilTest {

    @Test
    public void test() {
        Map<String, Object> dto = new HashMap<>();
        dto.put("carNo", "粤B-D23456");
        dto.put("mobile", "13700137000");
        String json = MaskMapUtil.toJSONString(dto);
        Map<String, Object> dto2 = new HashMap<>();
        dto2.put("carNo", "粤B-*****6");
        dto2.put("mobile", "137****7000");
        String json2 = JSON.toJSONString(dto2);
        Assert.assertEquals(json, json2);
    }
}
