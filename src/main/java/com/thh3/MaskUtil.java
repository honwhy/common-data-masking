package com.thh3;

import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson2.JSON;
import com.thh3.filter.fast.CarNoValueFilter;
import com.thh3.filter.fast.MobileValueFilter;

public class MaskUtil {

    public static String toJSONString(Object object) {
        return JSON.toJSONString(object, new ValueFilter[]{new CarNoValueFilter(), new MobileValueFilter()});
    }
}
