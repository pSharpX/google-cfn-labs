package com.onebank.taskmaster.notifier.consumer.email.config.sendgrid;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

@RequiredArgsConstructor
@Slf4j
public class SendGridClientRequestInterceptor implements RequestInterceptor {
	private final String apiKey;

	@Override
	public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", apiKey));
    }
}
