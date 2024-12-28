package com.onebank.taskmaster.sendnotification.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PropertiesPrefix(value = "task-master")
public class AppConfigProperties {
    private Notification notification;

    @Getter
    @Setter
    public static class Notification {
        private boolean enabled;
        private Consumer consumer;
    }

    @Getter
    @Setter
    public static class Consumer {
        private String channel;
        private String provider;
        private boolean provisioned;
    }
}
