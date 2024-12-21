package com.onebank.taskmaster.notifier.consumer.email.provider.sendgrid;

import com.onebank.taskmaster.notifier.consumer.model.sendgrid.ActivateTemplateRequest;
import com.onebank.taskmaster.notifier.consumer.model.sendgrid.CreateTemplateRequest;
import com.onebank.taskmaster.notifier.consumer.model.sendgrid.CreateTemplateResponse;
import com.onebank.taskmaster.notifier.consumer.model.sendgrid.CreateTemplateVersionRequest;
import com.onebank.taskmaster.notifier.consumer.model.sendgrid.SendMessageRequest;
import com.onebank.taskmaster.notifier.consumer.model.sendgrid.TemplateVersionResponse;
import com.onebank.taskmaster.notifier.consumer.model.sendgrid.UpdateTemplateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "sendgrid-sender", url = "${task-master.channel.email.sendgrid.basePath}")
public interface SendGridClient {

	@PostMapping(value = "/mail/send")
	void send(SendMessageRequest request);

	@PostMapping(value = "/templates")
	CreateTemplateResponse createTemplate(CreateTemplateRequest request);

	@PatchMapping(value = "/templates/{templateId}")
	CreateTemplateResponse editTemplate(@PathVariable String templateId, UpdateTemplateRequest request);

	@PostMapping(value = "/templates/{templateId}/versions")
	TemplateVersionResponse createTemplateVersion(@PathVariable String templateId, CreateTemplateVersionRequest request);

	@PostMapping(value = "/templates/{templateId}/versions/{versionId}")
	TemplateVersionResponse editTemplateVersion(@PathVariable String templateId, @PathVariable String versionId, CreateTemplateVersionRequest request);

	@PostMapping(value = "/templates/{templateId}/versions/{versionId}/activate")
	TemplateVersionResponse activateTemplateVersion(ActivateTemplateRequest request);
}
