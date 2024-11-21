package com.onebank.taskmaster.searchtask.service;

import com.google.inject.Inject;
import com.onebank.taskmaster.searchtask.config.AppConfigProperties;
import com.onebank.taskmaster.searchtask.config.ConfigProvider;
import com.onebank.taskmaster.searchtask.config.FunctionConfigProperties;
import com.onebank.taskmaster.searchtask.function.interceptors.Auditable;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Auditable
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class GetConfigPropertiesService implements GetConfigProperties {
    private final ConfigProvider configProvider;
    private final AppConfigProperties appConfigProperties;
    private final FunctionConfigProperties functionConfigProperties;

    @Override
    public String get(String propertyName) {
        AppConfigProperties _configProperties = configProvider.getConfig(AppConfigProperties.class);
        FunctionConfigProperties _functionConfigProperties = configProvider.getConfig(FunctionConfigProperties.class);
        return "";
    }

    @Override
    public List<String> retrieveAll(String prefixPropertyName) {
        AppConfigProperties _configProperties = configProvider.getConfig(AppConfigProperties.class);
        FunctionConfigProperties _functionConfigProperties = configProvider.getConfig(FunctionConfigProperties.class);
        return List.of();
    }
}
