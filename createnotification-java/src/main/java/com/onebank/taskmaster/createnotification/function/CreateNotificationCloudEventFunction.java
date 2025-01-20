package com.onebank.taskmaster.createnotification.function;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.functions.CloudEventsFunction;
import com.onebank.taskmaster.createnotification.exception.BadRequestException;
import com.onebank.taskmaster.createnotification.exception.ResourceNotFoundException;
import com.onebank.taskmaster.createnotification.function.exception.CloudEventFunctionExceptionHandler;
import com.onebank.taskmaster.createnotification.service.NotificationCreator;
import io.cloudevents.CloudEvent;
import lombok.RequiredArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

@RequiredArgsConstructor
public class CreateNotificationCloudEventFunction implements CloudEventsFunction {
    private final NotificationCreator notificationCreator;
    private final CloudEventFunctionExceptionHandler exceptionHandler;
    private final ObjectMapper objectMapper;

    @Override
    public void accept(CloudEvent event) {
        try {
            String cloudEventData = new String(Objects.requireNonNull(event.getData()).toBytes(), StandardCharsets.UTF_8);

        } catch (IllegalArgumentException ex) {
            exceptionHandler.handleBadRequestException(ex);
        } catch (BadRequestException ex) {
            exceptionHandler.handleBadRequestException(ex);
        } catch (ResourceNotFoundException ex) {
            exceptionHandler.handleResourceNotFoundException(ex);
        } catch (Exception ex) {
            exceptionHandler.handleInternalServerException(ex);
        }
    }
}
