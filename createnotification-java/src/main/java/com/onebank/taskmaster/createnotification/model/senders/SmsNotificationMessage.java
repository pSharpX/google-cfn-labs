package com.onebank.taskmaster.createnotification.model.senders;

import com.onebank.taskmaster.createnotification.model.NotificationChannel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class SmsNotificationMessage extends NotificationMessage {
    private String recipientPhoneNumber;
    private String senderName;
    private boolean isUnicode;
    private Map<String, String> vars = new HashMap<>();
    private String templateName;

    public SmsNotificationMessage() {
        super(NotificationChannel.SMS);
    }
}
