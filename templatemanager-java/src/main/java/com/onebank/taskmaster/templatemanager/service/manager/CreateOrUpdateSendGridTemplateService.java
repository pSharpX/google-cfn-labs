package com.onebank.taskmaster.templatemanager.service.manager;

import com.google.inject.Inject;
import com.onebank.taskmaster.templatemanager.email.provider.sendgrid.SendGridClient;
import com.onebank.taskmaster.templatemanager.email.provider.sendgrid.SendGridClientRequestFactory;
import com.onebank.taskmaster.templatemanager.model.ActionTemplateDto;
import com.onebank.taskmaster.templatemanager.service.storage.GetObjectContent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateOrUpdateSendGridTemplateService extends CreateOrUpdateTemplate {
    private final SendGridClient sendGridClient;
    private final SendGridClientRequestFactory clientRequestFactory;

    @Inject
    public CreateOrUpdateSendGridTemplateService(GetObjectContent getObjectContent, SendGridClient sendGridClient, SendGridClientRequestFactory clientRequestFactory) {
        super(getObjectContent);
        this.sendGridClient = sendGridClient;
        this.clientRequestFactory = clientRequestFactory;
    }

    @Override
    void execute(ActionTemplateDto actionTemplateDto) {

    }
}
