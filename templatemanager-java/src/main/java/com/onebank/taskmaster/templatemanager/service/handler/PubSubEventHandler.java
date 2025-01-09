package com.onebank.taskmaster.templatemanager.service.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.events.cloud.pubsub.v1.MessagePublishedData;
import com.onebank.taskmaster.templatemanager.model.SubEventType;
import io.cloudevents.CloudEvent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public abstract class PubSubEventHandler implements EventHandler {
    private final ObjectMapper objectMapper;

    @Override
    public void handle(CloudEvent event) throws Exception {
        MessagePublishedData messagePublishedData = objectMapper.readValue(Objects.requireNonNull(event.getData()).toBytes(), MessagePublishedData.class);
        this.handle(SubEventType.getByFullName(event.getType()), messagePublishedData);
    }

    abstract void handle(@NonNull SubEventType eventTye, @NonNull MessagePublishedData data);
}
