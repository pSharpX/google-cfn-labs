package com.onebank.taskmaster.templatemanager.service.manager;

import com.google.inject.Inject;
import com.onebank.taskmaster.templatemanager.model.ActionTemplateDto;
import com.onebank.taskmaster.templatemanager.service.storage.GetObjectContent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateOrUpdateMockTemplateService extends CreateOrUpdateTemplate {

    @Inject
    public CreateOrUpdateMockTemplateService(GetObjectContent getObjectContent) {
        super(getObjectContent);
    }

    @Override
    void execute(ActionTemplateDto actionTemplateDto) {
        log.debug("Executing Mock template provider for Create or Update template: template_name = [{}]", actionTemplateDto.getName());
    }
}
