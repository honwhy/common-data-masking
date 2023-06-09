package com.thh3.log;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.MarkerManager;
import org.junit.Test;

@Slf4j
public class MaskRewriteAppenderTest {
    private static final Logger logger = LogManager.getLogger();
    @Test
    public void test() {
        // no marker
        log.info("{}", "13700137000");
        org.apache.logging.log4j.Marker maskMark = MarkerManager.getMarker("MASK_MARKER");
        logger.info(maskMark, "{}", "13700137000");
        logger.info(maskMark, "{}", "粤B-D23456");
    }
}
