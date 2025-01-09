package com.onebank.taskmaster.templatemanager.email.provider.sendgrid;

import com.onebank.taskmaster.templatemanager.email.model.sendgrid.ActivateTemplateRequest;
import com.onebank.taskmaster.templatemanager.email.model.sendgrid.CreateTemplateRequest;
import com.onebank.taskmaster.templatemanager.email.model.sendgrid.CreateTemplateResponse;
import com.onebank.taskmaster.templatemanager.email.model.sendgrid.CreateTemplateVersionRequest;
import com.onebank.taskmaster.templatemanager.email.model.sendgrid.SendMessageRequest;
import com.onebank.taskmaster.templatemanager.email.model.sendgrid.TemplateVersionResponse;
import com.onebank.taskmaster.templatemanager.email.model.sendgrid.UpdateTemplateRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

@Headers("Content-Type: application/json")
public interface SendGridClient {

	@RequestLine("POST /mail/send")
	void send(SendMessageRequest request);

	@RequestLine(value = "POST /templates")
	CreateTemplateResponse createTemplate(CreateTemplateRequest request);

	@RequestLine(value = "PATCH /templates/{templateId}")
	CreateTemplateResponse editTemplate(@Param("templateId") String templateId, UpdateTemplateRequest request);

	@RequestLine(value = "GET /templates/{templateId}")
	CreateTemplateResponse retrieveSingleTemplate(@Param("templateId") String templateId);

	@RequestLine(value = "DELETE /templates/{templateId}")
	void deleteTemplate(@Param("templateId") String templateId);

	@RequestLine(value = "POST /templates/{templateId}/versions")
	TemplateVersionResponse createTemplateVersion(@Param("templateId") String templateId, CreateTemplateVersionRequest request);

	@RequestLine(value = "GET /templates/{templateId}/versions/{versionId}")
	TemplateVersionResponse retrieveSingleTemplateVersion(@Param("templateId") String templateId, @Param("versionId") String versionId);

	@RequestLine(value = "POST /templates/{templateId}/versions/{versionId}")
	TemplateVersionResponse editTemplateVersion(@Param("templateId") String templateId, @Param("versionId") String versionId, CreateTemplateVersionRequest request);

	@RequestLine(value = "POST /templates/{templateId}/versions/{versionId}/activate")
	TemplateVersionResponse activateTemplateVersion(ActivateTemplateRequest request);

	@RequestLine(value = "DELETE /templates/{templateId}/versions/{versionId}")
	void deleteTemplateVersion(@Param("templateId") String templateId, @Param("versionId") String versionId);
}
