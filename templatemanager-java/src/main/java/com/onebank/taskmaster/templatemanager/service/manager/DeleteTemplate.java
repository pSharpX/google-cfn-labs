package com.onebank.taskmaster.templatemanager.service.manager;

import com.google.events.cloud.storage.v1.StorageObjectData;
import com.onebank.taskmaster.templatemanager.model.ActionTemplateDto;
import com.onebank.taskmaster.templatemanager.model.ActionTemplateType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public abstract class DeleteTemplate implements ActionTemplate {

    @Override
    public void execute(StorageObjectData objectData) {
        log.debug("Processing CreateOrUpdateAction for [{}] template", objectData.getName());
        ActionTemplateDto actionTemplateDto = new ActionTemplateDto(ActionTemplateType.DELETE,
                objectData.getName(), objectData.getBucket(), null, objectData.getContentType(), objectData.getSize());

        this.execute(actionTemplateDto);
    }

    abstract void execute(ActionTemplateDto actionTemplateDto);
}
