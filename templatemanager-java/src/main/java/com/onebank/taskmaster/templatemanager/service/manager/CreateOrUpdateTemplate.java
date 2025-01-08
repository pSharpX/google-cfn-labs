package com.onebank.taskmaster.templatemanager.service.manager;

import com.google.events.cloud.storage.v1.StorageObjectData;
import com.onebank.taskmaster.templatemanager.model.ActionTemplateDto;
import com.onebank.taskmaster.templatemanager.model.ActionTemplateType;
import com.onebank.taskmaster.templatemanager.service.storage.GetObjectContent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public abstract class CreateOrUpdateTemplate implements ActionTemplate {
    private final GetObjectContent getObjectContent;

    @Override
    public void execute(StorageObjectData objectData) {
        log.debug("Processing CreateOrUpdateAction for [{}] template", objectData.getName());
        String content = getObjectContent.execute(objectData.getName(), objectData.getBucket());
        ActionTemplateDto actionTemplateDto = new ActionTemplateDto(ActionTemplateType.CREATE_OR_UPDATE,
                objectData.getName(), objectData.getBucket(), content, objectData.getContentType(), objectData.getSize());

        this.execute(actionTemplateDto);
    }

    abstract void execute(ActionTemplateDto actionTemplateDto);
}
