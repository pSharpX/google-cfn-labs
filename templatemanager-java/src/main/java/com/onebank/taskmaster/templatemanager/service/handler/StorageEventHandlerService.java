package com.onebank.taskmaster.templatemanager.service.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.events.cloud.storage.v1.StorageObjectData;
import com.google.inject.Inject;
import com.onebank.taskmaster.templatemanager.model.SubEventType;
import com.onebank.taskmaster.templatemanager.service.manager.ActionTemplateResolver;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StorageEventHandlerService extends StorageEventHandler {
    private final ActionTemplateResolver actionTemplateResolver;

    @Inject
    public StorageEventHandlerService(ObjectMapper objectMapper, ActionTemplateResolver actionTemplateResolver) {
        super(objectMapper);
        this.actionTemplateResolver = actionTemplateResolver;
    }

    @Override
    void handle(@NonNull String eventType, @NonNull StorageObjectData data) {
        actionTemplateResolver.resolve(SubEventType.getByName(eventType)).execute(data);
    }
}
