package com.thh3;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@AllArgsConstructor
@Data
public class SimpleDTO {
    private String val1;
    private String val2;

    public static void main(String[] args) throws IOException {
        SimpleDTO dto = new SimpleDTO("粤B-D23456", "13700137000");
        InputStream inputStream = dto.getClass().getClassLoader().getResourceAsStream("common-data-masking.properties");
        if (inputStream != null) {
            Properties properties = new Properties();
            properties.load(inputStream);
            Set<Map.Entry<Object, Object>> entries = properties.entrySet();
            for (Map.Entry<Object, Object> entry : entries) {
                System.out.println(entry.getKey() + "==>");
                System.out.println(entry.getValue());
            }
        }
    }
}
