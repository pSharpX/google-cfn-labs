package com.onebank.taskmaster.createnotification.notifier.config;

import com.onebank.taskmaster.createnotification.config.PropertiesPrefix;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PropertiesPrefix(value = "task-master.notification.rabbit")
public final class RabbitConfigProperties {
    private String topic;
    private String hostname;
    private int port;
    private String username;
    private String password;
    private String virtualhost;
    private String routingKey;
}
