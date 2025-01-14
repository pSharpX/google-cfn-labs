package com.onebank.taskmaster.templatemanager.modules;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.onebank.taskmaster.templatemanager.config.ConfigProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.zalando.logbook.CorrelationId;
import org.zalando.logbook.HttpLogFormatter;
import org.zalando.logbook.HttpLogWriter;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.RequestFilter;
import org.zalando.logbook.ResponseFilter;
import org.zalando.logbook.Sink;
import org.zalando.logbook.Strategy;
import org.zalando.logbook.attributes.AttributeExtractor;
import org.zalando.logbook.attributes.NoOpAttributeExtractor;
import org.zalando.logbook.core.*;
import org.zalando.logbook.json.JsonHttpLogFormatter;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Slf4j
@RequiredArgsConstructor
public class LogbookConfigModule extends AbstractModule {
    private final ConfigProvider configProvider;

    @Provides
    @Singleton
    @Named("logbookProperties")
    public Properties getLogbookConfig () {
        return configProvider.getConfig("logbook");
    }

    @Provides
    @Singleton
    // TODO: Fix logbook integration (with Function F. invoker)
    public Logbook logbook(
            final Strategy strategy,
            final CorrelationId correlationId,
            final RequestFilter requestFilter,
            final ResponseFilter responseFilter,
            final AttributeExtractor attributeExtractor,
            final Sink sink
    ) {
        return Logbook.builder()
                .correlationId(correlationId)
                .requestFilters(List.of(requestFilter))
                .responseFilters(List.of(responseFilter))
                .strategy(strategy)
                .attributeExtractor(attributeExtractor)
                .sink(sink)
                .build();
    }

    @Provides
    @Singleton
    public Strategy strategy(@Named("logbookProperties") Properties config) {
        String strategy = Optional.ofNullable(config.getProperty("strategy"))
                .orElse("default");
        return switch (strategy) {
            case "status-at-least" -> {
                int status = Optional.ofNullable(config.getProperty("minimum-status"))
                        .map(Integer::valueOf)
                        .orElse(400);
                yield new StatusAtLeastStrategy(status);
            }
            case "body-only-if-status-at-least" -> {
                int status = Optional.ofNullable(config.getProperty("minimum-status"))
                        .map(Integer::valueOf)
                        .orElse(400);
                yield new BodyOnlyIfStatusAtLeastStrategy(status);
            }
            case "without-body" -> new WithoutBodyStrategy();
            default -> new DefaultStrategy();
        };
    }

    @Provides
    @Singleton
    public CorrelationId correlationId() {
        return new DefaultCorrelationId();
    }

    @Provides
    @Singleton
    public RequestFilter requestFilter() {
        return RequestFilters.defaultValue();
    }

    @Provides
    @Singleton
    public ResponseFilter responseFilter() {
        return ResponseFilters.defaultValue();
    }

    @Provides
    @Singleton
    public AttributeExtractor getAttributeExtractor() {
        return new NoOpAttributeExtractor();
    }

    @Provides
    @Singleton
    public HttpLogFormatter httpFormatter(
            final @Named("logbookProperties") Properties config,
            final ObjectMapper mapper) {
        String strategy = Optional.ofNullable(config.getProperty("format.style"))
                .orElse("default");
        return switch (strategy) {
            case "curl" -> new CurlHttpLogFormatter();
            case "http" -> new DefaultHttpLogFormatter();
            case "splunk" -> new SplunkHttpLogFormatter();
            default -> new JsonHttpLogFormatter(mapper);
        };
    }

    @Provides
    @Singleton
    public HttpLogWriter writer() {
        return new DefaultHttpLogWriter();
    }

    @Provides
    @Singleton
    public Sink sink(final HttpLogFormatter formatter, final HttpLogWriter writer) {
        return new DefaultSink(formatter, writer);
    }
}
