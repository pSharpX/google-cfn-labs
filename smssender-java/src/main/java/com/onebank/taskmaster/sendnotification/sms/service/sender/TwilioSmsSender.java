package com.onebank.taskmaster.sendnotification.sms.service.sender;

import com.google.inject.Inject;
import com.onebank.taskmaster.sendnotification.model.senders.SmsNotificationMessage;
import com.onebank.taskmaster.sendnotification.sms.provider.twilio.TwilioClient;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@Slf4j
public class TwilioSmsSender implements SmsSender {
    private final TwilioClient client;

    @Override
    public void send(@NonNull SmsNotificationMessage notificationMessage) {
        log.debug("Sending [{}] notification with MailChimp Provider", notificationMessage.getChannel().getName());
    }
}
