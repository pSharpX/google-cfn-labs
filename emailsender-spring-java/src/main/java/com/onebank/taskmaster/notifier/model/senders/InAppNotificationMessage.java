package com.onebank.taskmaster.notifier.model.senders;

import com.onebank.taskmaster.notifier.model.NotificationChannel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InAppNotificationMessage extends NotificationMessage {
    private String recipientUserId;
    private String imageUrl;
    private String actionUrl;
    private boolean isRead;

    public InAppNotificationMessage() {
        super(NotificationChannel.IN_APP);
    }
}
