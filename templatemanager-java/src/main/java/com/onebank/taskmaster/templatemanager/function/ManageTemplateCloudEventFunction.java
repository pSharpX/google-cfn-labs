package com.onebank.taskmaster.templatemanager.function;

import com.google.cloud.functions.CloudEventsFunction;
import com.onebank.taskmaster.templatemanager.exception.BadRequestException;
import com.onebank.taskmaster.templatemanager.exception.ResourceNotFoundException;
import com.onebank.taskmaster.templatemanager.function.exception.CloudEventFunctionExceptionHandler;
import com.onebank.taskmaster.templatemanager.model.EventType;
import com.onebank.taskmaster.templatemanager.service.handler.EventHandlerResolver;
import io.cloudevents.CloudEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class ManageTemplateCloudEventFunction implements CloudEventsFunction {
    private final EventHandlerResolver eventHandler;
    private final CloudEventFunctionExceptionHandler exceptionHandler;

    @Override
    public void accept(CloudEvent event) {
        try {
            log.debug("Receiving [{}] event", event.getType());
            eventHandler.resolve(EventType.getByName(event.getType())).handle(event);
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
