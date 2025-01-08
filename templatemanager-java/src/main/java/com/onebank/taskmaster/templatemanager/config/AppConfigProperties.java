package com.onebank.taskmaster.templatemanager.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PropertiesPrefix(value = "task-master")
public class AppConfigProperties {
    private Notification notification = new Notification();

    @Getter
    @Setter
    public static class Notification {
        private boolean enabled = false;
        private Template template = new Template();
    }

    @Getter
    @Setter
    public static class Template {
        private String provider;
        private Storage storage = new Storage();
    }

    @Getter
    @Setter
    public static class Storage {
        private String projectId;
        private String locationId;
        private String serviceAccountKeyPath;
    }
}
