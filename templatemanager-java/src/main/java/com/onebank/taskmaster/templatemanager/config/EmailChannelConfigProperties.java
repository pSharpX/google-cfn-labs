package com.onebank.taskmaster.templatemanager.config;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@PropertiesPrefix(value = "task-master.channel")
public final class EmailChannelConfigProperties {
    private Map<String, Object> email;
}
