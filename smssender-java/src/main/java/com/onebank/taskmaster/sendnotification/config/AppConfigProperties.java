package com.onebank.taskmaster.sendnotification.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PropertiesPrefix(value = "task-master")
public final class AppConfigProperties {
    private Notification notification = new Notification();

    @Getter
    @Setter
    public static class Notification {
        private boolean enabled = false;
        private Consumer consumer = new Consumer();
    }

    @Getter
    @Setter
    public static class Consumer {
        private String channel;
        private String provider;
        private boolean provisioned = false;
    }
}
