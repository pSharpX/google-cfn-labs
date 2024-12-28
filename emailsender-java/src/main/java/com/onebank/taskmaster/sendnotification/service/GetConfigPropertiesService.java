package com.onebank.taskmaster.sendnotification.service;

import com.google.inject.Inject;
import com.onebank.taskmaster.sendnotification.config.AppConfigProperties;
import com.onebank.taskmaster.sendnotification.config.ConfigProvider;
import com.onebank.taskmaster.sendnotification.config.EmailChannelConfigProperties;
import com.onebank.taskmaster.sendnotification.function.interceptors.Auditable;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Auditable
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class GetConfigPropertiesService implements GetConfigProperties {
    private final ConfigProvider configProvider;
    private final AppConfigProperties appConfigProperties;
    private final EmailChannelConfigProperties emailChannelConfigProperties;

    @Override
    public String get(String propertyName) {
        AppConfigProperties _configProperties = configProvider.getConfig(AppConfigProperties.class);
        EmailChannelConfigProperties _emailChannelConfigProperties = configProvider.getConfig(EmailChannelConfigProperties.class);
        return "";
    }

    @Override
    public List<String> retrieveAll(String prefixPropertyName) {
        AppConfigProperties _configProperties = configProvider.getConfig(AppConfigProperties.class);
        EmailChannelConfigProperties _emailChannelConfigProperties = configProvider.getConfig(EmailChannelConfigProperties.class);
        return List.of();
    }
}
