package com.onebank.taskmaster.sendnotification.model.senders;

import com.onebank.taskmaster.sendnotification.model.NotificationChannel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmsNotificationMessage extends NotificationMessage {
    private String recipientPhoneNumber;
    private String senderName;
    private boolean isUnicode;

    public SmsNotificationMessage() {
        super(NotificationChannel.SMS);
    }
}
