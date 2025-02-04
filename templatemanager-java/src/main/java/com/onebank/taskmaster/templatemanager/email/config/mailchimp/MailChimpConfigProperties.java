package com.onebank.taskmaster.templatemanager.email.config.mailchimp;

import com.onebank.taskmaster.templatemanager.config.PropertiesPrefix;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PropertiesPrefix(value = "task-master.channel.email.mailchimp")
public final class MailChimpConfigProperties {
    private String url;
    private String apiVersion;
    private String basePath;
    private String apikey;
    private Config config;
    private From from;
    private Logger logger;

    @Getter
    @Setter
    public static class Config {
        private String mergeLanguage;
    }

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
