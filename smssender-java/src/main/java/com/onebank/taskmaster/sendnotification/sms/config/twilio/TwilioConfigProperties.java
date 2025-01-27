package com.onebank.taskmaster.sendnotification.sms.config.twilio;

import com.onebank.taskmaster.sendnotification.config.PropertiesPrefix;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PropertiesPrefix(value = "task-master.channel.sms.twilio")
public final class TwilioConfigProperties {
    private String url;
    private String apiVersion;
    private String basePath;
    private String accountSid;
    private MessageService messageService = new MessageService();
    private Credentials credentials = new Credentials();
    private String msId;
    private Logger logger = new Logger();

    @Getter
    @Setter
    public static class MessageService {
        private String url;
        private String apiVersion;
        private String basePath;
    }

    @Getter
    @Setter
    public static class Credentials {
        private String sid;
        private String secret;

        @Override
        public String toString() {
            return String.format("%s:%s", sid, secret);
        }
    }

    @Getter
    @Setter
    public static class Logger {
        private String level  = "FULL";
    }
}
