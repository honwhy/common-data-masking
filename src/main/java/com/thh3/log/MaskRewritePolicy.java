package com.thh3.log;


import com.thh3.filter.AllPattern;
import com.thh3.filter.fast.CarNoValueFilter;
import com.thh3.filter.fast.MobileValueFilter;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.rewrite.RewritePolicy;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.message.SimpleMessage;

import java.util.Objects;

@Plugin(
        name = "MaskRewritePolicy",
        category = "Core",
        elementType = "rewritePolicy",
        printObject = true
)
public class MaskRewritePolicy implements RewritePolicy {

    @Override
    public LogEvent rewrite(LogEvent source) {
        Marker marker = source.getMarker();
        if (marker != null && Objects.equals(marker.getName(), "MASK_MARKER")) {
            String message = source.getMessage().getFormattedMessage();
            if (message.matches(AllPattern.CAR_NO_PATTERN_QUALIFIED)) {
                message = new CarNoValueFilter().doMask(message);
            } else if (message.matches(AllPattern.MOBILE_PATTERN_QUALIFIED)) {
                message = new MobileValueFilter().doMask(message);
            }
            return (new Log4jLogEvent.Builder(source)).setMessage(new SimpleMessage(message)).build();
        }
        return source;
    }

    @PluginFactory
    public static MaskRewritePolicy createPolicy(@PluginAttribute("name") final String name) {
        return new MaskRewritePolicy();
    }
}
