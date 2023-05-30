package com.thh3;

import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson2.JSON;
import com.thh3.filter.fast.CarNoValueFilter;
import com.thh3.filter.fast.KeyMapValueFilter;
import com.thh3.filter.fast.MobileValueFilter;

public class MaskMapUtil {

    public static String toJSONString(Object object) {
        return JSON.toJSONString(object, new ValueFilter[]{
                new KeyMapValueFilter("carNo", new CarNoValueFilter()),
                new KeyMapValueFilter("mobile", new MobileValueFilter())
        });
    }
}
