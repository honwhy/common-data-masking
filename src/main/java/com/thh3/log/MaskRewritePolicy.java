package com.thh3.log;


import com.thh3.filter.AllPattern;
import com.thh3.filter.fast.CarNoValueFilter;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.rewrite.RewritePolicy;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.message.SimpleMessage;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Plugin(
        name = "MaskRewritePolicy",
        category = "Core",
        elementType = "rewritePolicy",
        printObject = true
)
public class MaskRewritePolicy implements RewritePolicy {

    static Pattern CAR_NO_PATTERN = Pattern.compile(AllPattern.CAR_NO_PATTERN_QUALIFIED);
    static Pattern MOBILE_PATTERN = Pattern.compile(AllPattern.MOBILE_PATTERN_QUALIFIED);
    @Override
    public LogEvent rewrite(LogEvent source) {
        Marker marker = source.getMarker();
        if (marker != null && Objects.equals(marker.getName(), "MASK_MARKER")) {
            String message = source.getMessage().getFormattedMessage();
            boolean flag[] = { false };
            message = filtered(message, CAR_NO_PATTERN, flag);
            if (flag[0]) {
                return (new Log4jLogEvent.Builder(source)).setMessage(new SimpleMessage(message)).build();
            }
            message = filtered(message, MOBILE_PATTERN, flag);
            if (flag[0]) {
                return (new Log4jLogEvent.Builder(source)).setMessage(new SimpleMessage(message)).build();
            }
            return (new Log4jLogEvent.Builder(source)).setMessage(new SimpleMessage(message)).build();
        }
        return source;
    }

    @PluginFactory
    public static MaskRewritePolicy createPolicy(@PluginAttribute("name") final String name) {
        return new MaskRewritePolicy();
    }

    private String filtered(String message, Pattern pattern, boolean flag[]) {
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            message = new CarNoValueFilter().doMask(message, pattern);
            flag[0] = true;
        }
        return message;
    }
}
