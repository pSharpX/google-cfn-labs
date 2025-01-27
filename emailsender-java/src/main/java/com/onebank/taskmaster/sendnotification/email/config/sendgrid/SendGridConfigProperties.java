package com.onebank.taskmaster.sendnotification.email.config.sendgrid;

import com.onebank.taskmaster.sendnotification.config.PropertiesPrefix;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PropertiesPrefix(value = "task-master.channel.email.sendgrid")
public final class SendGridConfigProperties {
    private String url;
    private String apiVersion;
    private String basePath;
    private String apikey;
    private From from = new From();
    private Logger logger = new Logger();

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
