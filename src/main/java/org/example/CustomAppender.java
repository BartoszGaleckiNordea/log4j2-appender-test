package org.example;

import org.apache.logging.log4j.core.*;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Plugin(
        name = "CustomAppender", category = Core.CATEGORY_NAME, elementType = Appender.ELEMENT_TYPE
)
public class CustomAppender extends AbstractAppender {

    private final ConcurrentMap<String, LogEvent> events = new ConcurrentHashMap<>();

    public CustomAppender(String name, Filter filter) {
        super(name, filter, null);
    }

    @PluginFactory
    public static CustomAppender factory(@PluginAttribute("name") String name, @PluginElement("Filter") Filter filter) {
        return new CustomAppender(name, filter);
    }

    @Override
    public void append(LogEvent event) {
        events.put(Instant.now().toString(), event);
    }

    public ConcurrentMap<String, LogEvent> getEvents() {
        return events;
    }
}
