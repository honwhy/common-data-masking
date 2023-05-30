package com.thh3.filter;

import java.util.regex.Pattern;

public class AllPattern {
    public final static Pattern CAR_NO_PATTERN = Pattern.compile("(.{3})(.*)(.)");
    public final static Pattern MOBILE_PATTERN = Pattern.compile("(.{3})(.*)(.{4})");
}