package com.onebank.taskmaster.templatemanager.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PropertiesPrefix(value = "task-master.notification")
public class AppConfigProperties {
    private boolean enabled = false;
    private Template template = new Template();

    @Getter
    @Setter
    public static class Template {
        private String provider;
        private Storage storage = new Storage();
    }

    @Getter
    @Setter
    public static class Storage {
        private boolean enabled = false;
        private String projectId;
        private String locationId;
        private String serviceAccountKeyPath;
    }
}
