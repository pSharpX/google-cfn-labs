package com.onebank.taskmaster.notifier.model.senders;

import com.onebank.taskmaster.notifier.model.NotificationChannel;
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
