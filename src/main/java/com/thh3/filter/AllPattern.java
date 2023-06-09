package com.thh3.filter;

import java.util.regex.Pattern;

public class AllPattern {
    public final static String CAR_NO_PATTERN_VALUE = "(.{3})(.*)(.)";
    public final static String CAR_NO_PATTERN_QUALIFIED = "([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领][A-Z]{1}-?)([0-9A-HJ-NP-Z]{3,4})([0-9A-HJ-NP-Z])";
    public final static Pattern CAR_NO_PATTERN = Pattern.compile(CAR_NO_PATTERN_VALUE);
    public final static String MOBILE_PATTERN_VALUE = "(.{3})(.*)(.{4})";
    public final static String MOBILE_PATTERN_QUALIFIED = "(1(?:3[0-9]|4[01456879]|5[0-35-9]|6[2567]|7[0-8]|8[0-9]|9[0-35-9]))([0-9]{4})([0-9]{4})";
    public final static Pattern MOBILE_PATTERN = Pattern.compile(MOBILE_PATTERN_VALUE);
}