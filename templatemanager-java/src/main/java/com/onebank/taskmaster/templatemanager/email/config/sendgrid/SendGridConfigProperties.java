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

    @Getter
    @Setter
    public static class From {
        private String email;
        private String name;
    }
}
