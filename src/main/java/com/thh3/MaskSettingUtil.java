package com.thh3;

import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson2.JSON;
import com.thh3.filter.fast.CarNoSettingValueFilter;
import com.thh3.filter.fast.MobileSettingValueFilter;
import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MaskSettingUtil {
    @Getter
    private Properties properties;
    private static MaskSettingUtil instance;
    public MaskSettingUtil() {
        this("common-data-masking.properties");
    }
    public MaskSettingUtil(String configPath) {
        Properties properties = new Properties();
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(configPath);
            properties.load(inputStream);
        } catch (IOException e) {
            // properties file not found
        } finally {
            this.properties = properties;
        }
    }
    public static String toJSONString(Object object) {
        MaskSettingUtil instance = getInstance();
        Properties props = instance.getProperties();
        return JSON.toJSONString(object, new ValueFilter[]{new CarNoSettingValueFilter(props), new MobileSettingValueFilter(props)});
    }

    private static MaskSettingUtil getInstance() {
        if (instance != null) {
            return instance;
        }
        synchronized (MaskSettingUtil.class) {
            if (instance != null) {
                return instance;
            }
            instance = new MaskSettingUtil();
        }
        return instance;
    }
}
