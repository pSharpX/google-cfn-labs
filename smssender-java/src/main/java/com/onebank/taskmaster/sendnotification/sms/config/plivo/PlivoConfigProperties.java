package com.onebank.taskmaster.sendnotification.sms.config.plivo;

import com.onebank.taskmaster.sendnotification.config.PropertiesPrefix;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PropertiesPrefix(value = "task-master.channel.sms.plivo")
public final class PlivoConfigProperties {
    private String url;
    private String apiVersion;
    private String basePath;
    private String accountSid;
    private String sid;
    private String secret;
    private String msId;
    private Logger logger;

    @Getter
    @Setter
    public static class Logger {
        private String level  = "FULL";
    }
}
