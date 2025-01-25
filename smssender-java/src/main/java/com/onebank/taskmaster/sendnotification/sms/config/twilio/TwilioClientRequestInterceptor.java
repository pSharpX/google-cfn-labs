package com.onebank.taskmaster.sendnotification.sms.config.twilio;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Base64;

@RequiredArgsConstructor
@Slf4j
public class TwilioClientRequestInterceptor implements RequestInterceptor {
	private final TwilioConfigProperties.Credentials credentials;

	@Override
	public void apply(RequestTemplate requestTemplate) {
		requestTemplate.header("Authorization", String.format("Basic %s", Base64.getEncoder().encodeToString(credentials.toString().getBytes())));
	}
}
