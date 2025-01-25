package com.onebank.taskmaster.sendnotification.sms.provider.twilio;

import feign.Headers;
import feign.RequestLine;

@Headers("Content-Type: application/json")
public interface TwilioMessageServiceClient {

	@RequestLine("POST /users/ping")
	String ping();
}
