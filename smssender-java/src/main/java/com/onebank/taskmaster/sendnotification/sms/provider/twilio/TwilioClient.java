package com.onebank.taskmaster.sendnotification.sms.provider.twilio;

import com.onebank.taskmaster.sendnotification.sms.model.twilio.SendMessageParam;
import com.onebank.taskmaster.sendnotification.sms.model.twilio.SendMessageResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

@Headers({
		"Content-Type: application/x-www-form-urlencoded",
		"Accept: application/json",
})
public interface TwilioClient {

	@RequestLine("POST /Accounts/{accountSid}/Messages.json")
	SendMessageResponse sendMessage(@Param("accountSid") String accountSid, SendMessageParam param);
}
