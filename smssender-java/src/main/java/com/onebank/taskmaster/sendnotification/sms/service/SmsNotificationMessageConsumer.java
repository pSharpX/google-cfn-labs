package com.onebank.taskmaster.sendnotification.sms.service;

import com.google.inject.Inject;

import com.onebank.taskmaster.sendnotification.function.interceptors.Auditable;
import com.onebank.taskmaster.sendnotification.model.senders.NotificationMessage;
import com.onebank.taskmaster.sendnotification.model.senders.SmsNotificationMessage;
import com.onebank.taskmaster.sendnotification.service.NotificationMessageConsumer;
import com.onebank.taskmaster.sendnotification.sms.service.sender.SmsSender;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@Slf4j
public class SmsNotificationMessageConsumer implements NotificationMessageConsumer {
    private final SmsSender smsSender;

    @Override
    @Auditable
    public void send(@NonNull NotificationMessage message) {
        log.debug("Sending message via sender-provider");
        smsSender.send((SmsNotificationMessage) message);
    }
}
