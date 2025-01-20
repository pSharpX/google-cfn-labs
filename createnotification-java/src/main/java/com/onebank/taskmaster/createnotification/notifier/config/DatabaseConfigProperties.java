package com.onebank.taskmaster.createnotification.notifier.config;

import com.onebank.taskmaster.createnotification.config.PropertiesPrefix;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PropertiesPrefix(value = "task-master.datasource.control-plane")
public final class DatabaseConfigProperties {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
}
