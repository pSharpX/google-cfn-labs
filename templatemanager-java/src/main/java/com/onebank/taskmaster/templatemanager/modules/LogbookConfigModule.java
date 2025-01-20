package com.onebank.taskmaster.templatemanager.modules;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.onebank.taskmaster.templatemanager.config.ConfigProvider;
import com.onebank.taskmaster.templatemanager.config.LogbookProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.zalando.logbook.*;
import org.zalando.logbook.attributes.AttributeExtractor;
import org.zalando.logbook.attributes.NoOpAttributeExtractor;
import org.zalando.logbook.core.*;
import org.zalando.logbook.json.JacksonJsonFieldBodyFilter;
import org.zalando.logbook.json.JsonHttpLogFormatter;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import static org.zalando.logbook.core.HeaderFilters.replaceHeaders;
import static org.zalando.logbook.core.QueryFilters.replaceQuery;

@Slf4j
@RequiredArgsConstructor
public class LogbookConfigModule extends AbstractModule {

    @Provides
    @Singleton
    public LogbookProperties provideLogbookProperties(final ConfigProvider configProvider) {
        return configProvider.getConfig(LogbookProperties.class);
    }

    @Provides
    @Singleton
    @Named("logbookProperties")
    public Properties getLogbookConfig (final ConfigProvider configProvider) {
        return configProvider.getConfig("logbook");
    }

    @Provides
    @Singleton
    // TODO: Fix logbook integration (with Function F. invoker)
    public Logbook logbook(
            final Strategy strategy,
            final CorrelationId correlationId,
            final QueryFilter queryFilter,
            final HeaderFilter headerFilter,
            final BodyFilter bodyFilter,
            final RequestFilter requestFilter,
            final ResponseFilter responseFilter,
            final AttributeExtractor attributeExtractor,
            final Sink sink
    ) {
        Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
        return Logbook.builder()
                .correlationId(correlationId)
                .queryFilters(List.of(queryFilter))
                .headerFilters(List.of(headerFilter))
                .bodyFilters(List.of(bodyFilter))
                .requestFilters(List.of(requestFilter))
                .responseFilters(List.of(responseFilter))
                .strategy(strategy)
                .attributeExtractor(attributeExtractor)
                .sink(sink)
                .build();
    }

    @Provides
    @Singleton
    public Strategy strategy(final @Named("logbookProperties") Properties config) {
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
    public Sink sink(
            final @Named("logbookProperties") Properties config,
            final HttpLogFormatter formatter,
            final HttpLogWriter writer) {
        Sink defaultSink = new DefaultSink(formatter, writer);
        String chunkSize = config.getProperty("write.chunk-size");
        if (Objects.nonNull(chunkSize)) {
            return new ChunkingSink(defaultSink, Integer.parseInt(chunkSize));
        }
        return new DefaultSink(formatter, writer);
    }

    @Provides
    @Singleton
    public QueryFilter queryFilter(final LogbookProperties properties) {
        final List<String> parameters = properties.getObfuscate().getParameters();
        return parameters.isEmpty() ?
                QueryFilters.defaultValue() :
                replaceQuery(new HashSet<>(parameters)::contains, properties.getObfuscate().getReplacement());
    }

    @Provides
    @Singleton
    public HeaderFilter headerFilter(final LogbookProperties properties) {
        final Set<String> headers = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        headers.addAll(properties.getObfuscate().getHeaders());

        return headers.isEmpty() ?
                HeaderFilters.defaultValue() :
                replaceHeaders(headers, properties.getObfuscate().getReplacement());
    }

    @Provides
    @Singleton
    public PathFilter pathFilter(final LogbookProperties properties) {
        final List<String> paths = properties.getObfuscate().getPaths();
        return paths.isEmpty() ?
                PathFilter.none() :
                paths.stream()
                        .map(path -> PathFilters.replace(path, properties.getObfuscate().getReplacement()))
                        .reduce(PathFilter::merge)
                        .orElseGet(PathFilter::none);
    }

    @Provides
    @Singleton
    public BodyFilter jsonBodyFieldsFilter(final LogbookProperties properties) {
        final LogbookProperties.Obfuscate obfuscate = properties.getObfuscate();
        final List<String> jsonBodyFields = obfuscate.getJsonBodyFields();
        if (jsonBodyFields.isEmpty()) {
            return BodyFilter.none();
        }
        return new JacksonJsonFieldBodyFilter(jsonBodyFields, obfuscate.getReplacement());
    }
}
