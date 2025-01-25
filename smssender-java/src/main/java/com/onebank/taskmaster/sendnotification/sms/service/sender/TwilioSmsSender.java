package com.onebank.taskmaster.sendnotification.sms.service.sender;

import com.google.inject.Inject;
import com.onebank.taskmaster.sendnotification.model.senders.SmsNotificationMessage;
import com.onebank.taskmaster.sendnotification.sms.model.twilio.SendMessageResponse;
import com.onebank.taskmaster.sendnotification.sms.provider.twilio.TwilioClient;
import com.onebank.taskmaster.sendnotification.sms.provider.twilio.TwilioClientRequestFactory;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@Slf4j
public class TwilioSmsSender implements SmsSender {
    private final TwilioClient client;
    private final TwilioClientRequestFactory requestFactory;

    @Override
    public void send(@NonNull SmsNotificationMessage notificationMessage) {
        log.debug("Sending [{}] notification with Twilio Provider", notificationMessage.getChannel().getName());
        SendMessageResponse response = client.sendMessage(requestFactory.getAccountSid(), requestFactory.build(notificationMessage));
        log.debug(response.toString());
    }
}
