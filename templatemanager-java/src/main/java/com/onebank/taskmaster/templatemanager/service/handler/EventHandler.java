package com.onebank.taskmaster.templatemanager.service.handler;

import io.cloudevents.CloudEvent;

public interface EventHandler {
    void handle(CloudEvent event) throws Exception;
}
