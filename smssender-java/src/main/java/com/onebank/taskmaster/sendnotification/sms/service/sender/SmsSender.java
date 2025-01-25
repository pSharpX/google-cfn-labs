package com.onebank.taskmaster.sendnotification.sms.service.sender;

import com.onebank.taskmaster.sendnotification.model.senders.SmsNotificationMessage;
import lombok.NonNull;

public interface SmsSender {
    void send(@NonNull SmsNotificationMessage notificationMessage);
}
