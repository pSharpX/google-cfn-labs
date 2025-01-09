package com.onebank.taskmaster.templatemanager.service.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.events.cloud.pubsub.v1.MessagePublishedData;
import com.google.inject.Inject;
import com.onebank.taskmaster.templatemanager.model.SubEventType;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PubSubEventHandlerService extends PubSubEventHandler {
    private final ObjectMapper objectMapper;

    @Inject
    public PubSubEventHandlerService(ObjectMapper objectMapper) {
        super(objectMapper);
        this.objectMapper = objectMapper;
    }

    @Override
    void handle(@NonNull SubEventType eventType, @NonNull MessagePublishedData data) {
        log.debug("Handling Pub/Sub [{}] event", eventType.getName());
    }
}
