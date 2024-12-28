package com.onebank.taskmaster.sendnotification.config;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@PropertiesPrefix(value = "task-master.channel")
public class EmailChannelConfigProperties {
    private Map<String, Object> email;
}
