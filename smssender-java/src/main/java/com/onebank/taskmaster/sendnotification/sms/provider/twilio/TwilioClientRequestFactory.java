package com.onebank.taskmaster.sendnotification.sms.provider.twilio;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.onebank.taskmaster.sendnotification.helper.FunctionUtils;
import com.onebank.taskmaster.sendnotification.model.senders.SmsNotificationMessage;
import com.onebank.taskmaster.sendnotification.sms.config.twilio.TwilioConfigProperties;
import com.onebank.taskmaster.sendnotification.sms.model.twilio.SendMessageParam;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class TwilioClientRequestFactory {
    private final TwilioConfigProperties config;
    private final ObjectMapper objectMapper;

    public SendMessageParam build(SmsNotificationMessage notificationMessage) {
        SendMessageParam param = new SendMessageParam();
        param.setContentSid(notificationMessage.getTemplateName());
        param.setMessagingServiceSid(config.getMsId());
        param.setContentVariables(FunctionUtils.toJson(objectMapper, notificationMessage.getVars()));
        param.setTo(notificationMessage.getRecipientPhoneNumber());
        return param;
    }

    public String getAccountSid() {
        return config.getAccountSid();
    }
}
