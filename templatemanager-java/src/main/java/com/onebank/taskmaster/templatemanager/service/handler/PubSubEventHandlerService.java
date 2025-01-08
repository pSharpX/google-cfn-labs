package com.onebank.taskmaster.templatemanager.service.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.events.cloud.pubsub.v1.MessagePublishedData;
import com.google.inject.Inject;
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
    void handle(@NonNull String eventType, @NonNull MessagePublishedData data) {

    }
}
