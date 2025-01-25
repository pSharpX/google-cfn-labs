package com.onebank.taskmaster.sendnotification.sms.service.sender;

import com.google.inject.Inject;
import com.onebank.taskmaster.sendnotification.model.senders.SmsNotificationMessage;
import com.onebank.taskmaster.sendnotification.sms.provider.plivo.PlivoClient;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@Slf4j
public class PlivoSmsSender implements SmsSender {
    private final PlivoClient client;

    @Override
    public void send(@NonNull SmsNotificationMessage notificationMessage) {
        log.debug("Sending [{}] notification with Plivo Provider", notificationMessage.getChannel().getName());
    }
}
