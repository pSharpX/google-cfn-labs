package com.onebank.taskmaster.searchtask.config;

import com.google.inject.Inject;
import com.onebank.taskmaster.searchtask.helper.PropertiesMapper;
import lombok.RequiredArgsConstructor;

import java.util.Properties;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class ConfigProvider {
    private final Properties properties;

    public <T> T getConfig(Class<T> pojoClass) {
        return PropertiesMapper.mapPropertiesToPojo(properties, pojoClass);
    }
}
