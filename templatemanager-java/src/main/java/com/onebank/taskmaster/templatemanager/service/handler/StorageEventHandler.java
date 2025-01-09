package com.onebank.taskmaster.templatemanager.service.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.events.cloud.storage.v1.StorageObjectData;
import com.onebank.taskmaster.templatemanager.model.SubEventType;
import io.cloudevents.CloudEvent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public abstract class StorageEventHandler implements EventHandler {
    private final ObjectMapper objectMapper;

    @Override
    public void handle(CloudEvent event) throws Exception {
        StorageObjectData storageObjectData = objectMapper.readValue(Objects.requireNonNull(event.getData()).toBytes(), StorageObjectData.class);
        this.handle(SubEventType.getByFullName(event.getType()), storageObjectData);
    }

    abstract void handle(@NonNull SubEventType eventType, @NonNull StorageObjectData data);

}
