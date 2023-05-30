package com.thh3.fast;

import com.alibaba.fastjson.JSON;
import com.thh3.AnyDTO;
import com.thh3.MaskUtil;
import org.junit.Assert;
import org.junit.Test;

public class MaskUtilTest {

    @Test
    public void test() {
        AnyDTO dto = new AnyDTO("粤B-D23456", "13700137000");
        String json = MaskUtil.toJSONString(dto);
        AnyDTO dto2 = new AnyDTO("粤B-*****6", "137****7000");
        String json2 = JSON.toJSONString(dto2);
        Assert.assertEquals(json, json2);
    }

}
