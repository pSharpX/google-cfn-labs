package com.onebank.taskmaster.templatemanager.service.manager;

import com.onebank.taskmaster.templatemanager.model.ActionTemplateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class DeleteMockTemplateService extends DeleteTemplate {

    @Override
    void execute(ActionTemplateDto actionTemplateDto) {
        log.debug("Executing Mock template provider for Delete template: template_name = [{}]", actionTemplateDto.getName());
    }
}
