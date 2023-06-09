package com.thh3.filter.fast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface MarkFilter {

    String doMask(Object propertyValue);

    default String doMask(Object propertyValue, Pattern pattern) {
        Matcher matcher = pattern.matcher(String.valueOf(propertyValue));
        if (matcher.find()) {
            String g1 = matcher.group(1);
            String g2 = matcher.group(2);
            String g3 = matcher.group(3);
            StringBuilder sb = new StringBuilder(10);
            sb.append(g1);
            sb.append(g2.replaceAll(".","*"));
            sb.append(g3);
            return sb.toString();
        }
        return null;
    }
}
