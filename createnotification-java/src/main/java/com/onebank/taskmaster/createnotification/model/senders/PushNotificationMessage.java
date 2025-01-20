package com.onebank.taskmaster.createnotification.model.senders;

import com.onebank.taskmaster.createnotification.model.NotificationChannel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PushNotificationMessage extends NotificationMessage {
    private String recipientDeviceId;
    private String imageUrl;
    private String actionUrl;
    private boolean isSilent;

    public PushNotificationMessage() {
        super(NotificationChannel.PUSH);
    }
}
