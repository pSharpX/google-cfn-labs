package com.onebank.taskmaster.notifier.consumer.email.config.mailchimp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class MailChimpClientRequestInterceptor implements RequestInterceptor {
	private final String apiKey;

	@Override
	public void apply(RequestTemplate requestTemplate) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			ObjectNode jsonNode = (ObjectNode) objectMapper.readTree(requestTemplate.body());
			jsonNode.put("key", this.apiKey);
			String newBody = objectMapper.writeValueAsString(jsonNode);
			requestTemplate.body(newBody);
		} catch (IOException e) {
			log.error("Error when setting Mandrill api key", e);
		}
	}
}
