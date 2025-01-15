package com.onebank.taskmaster.templatemanager.service.manager;

import com.google.inject.Inject;
import com.onebank.taskmaster.templatemanager.model.ActionTemplateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@Slf4j
public class DeleteSendGridTemplateService extends DeleteTemplate {

    @Override
    void execute(ActionTemplateDto actionTemplateDto) {

    }
}
