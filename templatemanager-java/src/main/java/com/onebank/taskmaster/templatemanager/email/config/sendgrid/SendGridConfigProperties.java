package com.onebank.taskmaster.templatemanager.email.config.sendgrid;

import com.onebank.taskmaster.templatemanager.config.PropertiesPrefix;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PropertiesPrefix(value = "task-master.channel.email.sendgrid")
public class SendGridConfigProperties {
    private String url;
    private String apiVersion;
    private String basePath;
    private String apikey;
    private From from;
    private Logger logger;

    @Getter
    @Setter
    public static class From {
        private String email;
        private String name;
    }

    @Getter
    @Setter
    public static class Logger {
        private String level  = "FULL";
    }
}
