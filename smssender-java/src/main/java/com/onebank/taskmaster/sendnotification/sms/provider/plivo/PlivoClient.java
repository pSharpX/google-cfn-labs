package com.onebank.taskmaster.sendnotification.sms.provider.plivo;

import feign.Headers;
import feign.RequestLine;

@Headers("Content-Type: application/json")
public interface PlivoClient {

	@RequestLine("POST /users/ping")
	String ping();
}
