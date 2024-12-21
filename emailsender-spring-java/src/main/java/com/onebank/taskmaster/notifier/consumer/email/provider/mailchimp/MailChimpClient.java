package com.onebank.taskmaster.notifier.consumer.email.provider.mailchimp;

import com.onebank.taskmaster.notifier.consumer.model.mailchimp.CreateTemplateRequest;
import com.onebank.taskmaster.notifier.consumer.model.mailchimp.PublishTemplateRequest;
import com.onebank.taskmaster.notifier.consumer.model.mailchimp.SendMessageRequest;
import com.onebank.taskmaster.notifier.consumer.model.mailchimp.SendMessageResponse;
import com.onebank.taskmaster.notifier.consumer.model.mailchimp.SendMessageWithTemplateRequest;
import com.onebank.taskmaster.notifier.consumer.model.mailchimp.TemplateDetailResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "mailchimp-sender", url = "${task-master.channel.email.mailchimp.basePath}")
public interface MailChimpClient {

	@PostMapping(value = "/users/ping")
	ResponseEntity<String> ping();

	@PostMapping(value = "/messages/send")
	List<SendMessageResponse> send(SendMessageRequest request);

	@PostMapping(value = "/messages/send-template")
	List<SendMessageResponse> sendWithTemplate(SendMessageWithTemplateRequest request);

	@PostMapping(value = "/templates/add")
	TemplateDetailResponse addTemplate(CreateTemplateRequest request);

	@PostMapping(value = "/templates/update")
	TemplateDetailResponse updateTemplate(CreateTemplateRequest request);

	@PostMapping(value = "/templates/publish")
	TemplateDetailResponse publishTemplate(PublishTemplateRequest request);
}
