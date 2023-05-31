package com.thh3.fast;

import com.alibaba.fastjson.JSON;
import com.thh3.MaskSettingUtil;
import com.thh3.SimpleDTO;
import org.junit.Assert;
import org.junit.Test;

public class MaskSettingUtilTest {

    @Test
    public void test() {
        SimpleDTO dto = new SimpleDTO("粤B-D23456", "13700137000");
        String json = MaskSettingUtil.toJSONString(dto);
        SimpleDTO dto2 = new SimpleDTO("粤B-*****6", "137****7000");
        String json2 = JSON.toJSONString(dto2);
        Assert.assertEquals(json, json2);
    }
}
