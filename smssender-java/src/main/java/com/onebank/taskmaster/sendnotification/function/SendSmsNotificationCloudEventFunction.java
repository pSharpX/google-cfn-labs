package com.onebank.taskmaster.sendnotification.function;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.functions.CloudEventsFunction;
import com.google.events.cloud.pubsub.v1.MessagePublishedData;
import com.onebank.taskmaster.sendnotification.exception.BadRequestException;
import com.onebank.taskmaster.sendnotification.exception.ResourceNotFoundException;
import com.onebank.taskmaster.sendnotification.function.exception.CloudEventFunctionExceptionHandler;
import com.onebank.taskmaster.sendnotification.model.senders.NotificationMessage;
import com.onebank.taskmaster.sendnotification.service.NotificationMessageConsumer;
import io.cloudevents.CloudEvent;
import lombok.RequiredArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

@RequiredArgsConstructor
public class SendSmsNotificationCloudEventFunction implements CloudEventsFunction {
    private final NotificationMessageConsumer notificationMessageConsumer;
    private final CloudEventFunctionExceptionHandler exceptionHandler;
    private final ObjectMapper objectMapper;

    @Override
    public void accept(CloudEvent event) {
        try {
            String cloudEventData = new String(Objects.requireNonNull(event.getData()).toBytes(), StandardCharsets.UTF_8);

            MessagePublishedData messagePublishedData = objectMapper.readValue(cloudEventData, MessagePublishedData.class);
            NotificationMessage notificationMessage = objectMapper.readValue(messagePublishedData.getMessage().getData().toByteArray(), NotificationMessage.class);
            notificationMessageConsumer.send(notificationMessage);
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
