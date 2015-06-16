package com.workable.dropwizard.honeybadger;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.Layout;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.workable.honeybadger.logback.HoneybadgerAppender;
import io.dropwizard.logging.AbstractAppenderFactory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

import static com.google.common.base.Preconditions.checkNotNull;

@JsonTypeName("honeybadger")
public class HoneybadgerAppenderFactory extends AbstractAppenderFactory {

    @JsonProperty
    @NotNull
    private Level threshold = Level.ALL;

    @JsonProperty
    @NotEmpty
    private String apiKey;

    @JsonProperty
    private boolean async = true;

    @JsonProperty
    private String ignoredSystemProperties = null;

    @JsonProperty
    private int maxThreads = Runtime.getRuntime().availableProcessors();

    @JsonProperty
    private int priority;

    @JsonProperty
    private int queueSize;

    @JsonProperty
    private String ignoredExceptions = null;

    public Level getThreshold() {
        return threshold;
    }

    public void setThreshold(Level threshold) {
        this.threshold = threshold;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public boolean isAsync() {
        return async;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    public String getIgnoredSystemProperties() {
        return ignoredSystemProperties;
    }

    public void setIgnoredSystemProperties(String ignoredSystemProperties) {
        this.ignoredSystemProperties = ignoredSystemProperties;
    }

    public int getMaxThreads() {
        return maxThreads;
    }

    public void setMaxThreads(int maxThreads) {
        this.maxThreads = maxThreads;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }

    public String getIgnoredExceptions() {
        return ignoredExceptions;
    }

    public void setIgnoredExceptions(String ignoredExceptions) {
        this.ignoredExceptions = ignoredExceptions;
    }

    public Appender<ILoggingEvent> build(LoggerContext context, String applicationName, Layout<ILoggingEvent> layout) {
        checkNotNull(context);

        final HoneybadgerAppender appender = new HoneybadgerAppender();
        appender.setContext(context);
        appender.setApiKey(apiKey);
        appender.setAsync(async);
        appender.setIgnoredExceptions(ignoredExceptions);
        appender.setIgnoredSystemProperties(ignoredSystemProperties);
        appender.setMaxThreads(maxThreads);
        appender.setPriority(priority);
        appender.setQueueSize(queueSize);
        addThresholdFilter(appender, threshold);
        appender.start();

        return wrapAsync(appender);
    }
}
