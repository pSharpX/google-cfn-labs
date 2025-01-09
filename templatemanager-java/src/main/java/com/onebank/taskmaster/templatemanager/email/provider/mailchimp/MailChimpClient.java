package com.onebank.taskmaster.templatemanager.email.provider.mailchimp;

import com.onebank.taskmaster.templatemanager.email.model.mailchimp.CreateTemplateRequest;
import com.onebank.taskmaster.templatemanager.email.model.mailchimp.GetTemplateInfoRequest;
import com.onebank.taskmaster.templatemanager.email.model.mailchimp.PublishTemplateRequest;
import com.onebank.taskmaster.templatemanager.email.model.mailchimp.SendMessageRequest;
import com.onebank.taskmaster.templatemanager.email.model.mailchimp.SendMessageResponse;
import com.onebank.taskmaster.templatemanager.email.model.mailchimp.SendMessageWithTemplateRequest;
import com.onebank.taskmaster.templatemanager.email.model.mailchimp.TemplateDetailResponse;
import feign.Headers;
import feign.RequestLine;

import java.util.List;

public interface MailChimpClient {

	@RequestLine("POST /users/ping")
	String ping();

	@RequestLine("POST /messages/send")
	@Headers("Content-Type: application/json")
	List<SendMessageResponse> send(SendMessageRequest request);

	@RequestLine("POST /messages/send-template")
	@Headers("Content-Type: application/json")
	List<SendMessageResponse> sendWithTemplate(SendMessageWithTemplateRequest request);

	@RequestLine("POST /templates/add")
	@Headers("Content-Type: application/json")
	TemplateDetailResponse addTemplate(CreateTemplateRequest request);

	@RequestLine("POST /templates/info")
	@Headers("Content-Type: application/json")
	TemplateDetailResponse getTemplateInfo(GetTemplateInfoRequest request);

	@RequestLine("POST /templates/update")
	@Headers("Content-Type: application/json")
	TemplateDetailResponse updateTemplate(CreateTemplateRequest request);

	@RequestLine("POST /templates/publish")
	@Headers("Content-Type: application/json")
	TemplateDetailResponse publishTemplate(PublishTemplateRequest request);

	@RequestLine("POST /templates/delete")
	@Headers("Content-Type: application/json")
	TemplateDetailResponse deleteTemplate(GetTemplateInfoRequest request);

}
