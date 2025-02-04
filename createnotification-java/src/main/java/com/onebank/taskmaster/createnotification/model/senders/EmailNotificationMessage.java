package com.onebank.taskmaster.createnotification.model.senders;

import com.onebank.taskmaster.createnotification.model.NotificationChannel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class EmailNotificationMessage extends NotificationMessage {
    private String recipientName;
    private String recipientEmail;
    private Map<String, String> vars = new HashMap<>();
    private String templateName;

    public EmailNotificationMessage() {
        super(NotificationChannel.EMAIL);
    }
}
