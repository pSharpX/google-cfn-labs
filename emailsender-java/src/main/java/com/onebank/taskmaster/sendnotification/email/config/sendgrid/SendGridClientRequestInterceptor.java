package com.onebank.taskmaster.sendnotification.email.config.sendgrid;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class SendGridClientRequestInterceptor implements RequestInterceptor {
	private final String apiKey;

	@Override
	public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Authorization", String.format("Bearer %s", apiKey));
    }
}
