package com.onebank.taskmaster.sendnotification.email.config.mailchimp;

import com.onebank.taskmaster.sendnotification.config.PropertiesPrefix;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PropertiesPrefix(value = "task-master.channel.email.mailchimp")
public class MailChimpConfigProperties {
    private String url;
    private String apiVersion;
    private String basePath;
    private String apikey;
    private Config config;
    private From from;

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
}
