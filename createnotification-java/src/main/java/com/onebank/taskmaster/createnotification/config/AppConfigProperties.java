package com.onebank.taskmaster.createnotification.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PropertiesPrefix(value = "task-master")
public final class AppConfigProperties {
    private Database database = new Database();
    private Notification notification = new Notification();

    @Getter
    @Setter
    public static class Database {
        private boolean enabled;
    }

    @Getter
    @Setter
    public static class Notification {
        private boolean enabled = false;
        private String engine;
        private boolean provisioned;
    }
}
