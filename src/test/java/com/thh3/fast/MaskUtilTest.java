package com.thh3.fast;

import com.thh3.AnyDTO;
import com.thh3.MaskUtil;
import org.junit.Test;

public class MaskUtilTest {

    @Test
    public void test() {
        AnyDTO dto = new AnyDTO("粤B-D23456", "13700137000");
        String json = MaskUtil.toJSONString(dto);
        System.out.println(json);
    }

}
