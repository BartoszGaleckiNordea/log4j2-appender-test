package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.junit.jupiter.api.*;

public class CustomAppenderTest {

    @Test
    public void syncAppenderShouldGetAllMessages() {

        Logger logger = LogManager.getLogger("SynchronousLogger");

        LoggerContext context = LoggerContext.getContext(false);
        Configuration config = context.getConfiguration();
        CustomAppender appender = config.getAppender("CustomAppender");
        int eventSizeBeforeLog = appender.getEvents().size();

        for (int i = 0; i < 10_000; i++) {
            logger.info("Message");
        }

        Assertions.assertEquals(eventSizeBeforeLog + 10_000, appender.getEvents().size(), "Appender has to get 10_000 messages");
    }

    @Test
    public void asyncAppenderShouldGetAllMessages() {

        Logger logger = LogManager.getLogger("AsynchronousLogger");

        LoggerContext context = LoggerContext.getContext(false);
        Configuration config = context.getConfiguration();
        CustomAppender appender = config.getAppender("CustomAppender");
        int eventSizeBeforeLog = appender.getEvents().size();

        for (int i = 0; i < 10_000; i++) {
            logger.info("Message");
        }

        Assertions.assertEquals(eventSizeBeforeLog + 10_000, appender.getEvents().size(), "Appender has to get 10_000 messages");
    }

}
