package com.onebank.taskmaster.notifier.consumer.email.function;

import com.onebank.taskmaster.notifier.consumer.email.exception.BadRequestException;
import com.onebank.taskmaster.notifier.consumer.email.exception.ResourceNotFoundException;
import com.onebank.taskmaster.notifier.consumer.email.function.exception.FunctionExceptionHandler;
import com.onebank.taskmaster.notifier.consumer.service.NotificationMessageConsumer;
import com.onebank.taskmaster.notifier.model.senders.EmailNotificationMessage;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.function.adapter.gcp.FunctionInvoker;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.Validator;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class SendEmailNotificationFunction implements Function<EmailNotificationMessage, Message<String>> {

    private final NotificationMessageConsumer notificationMessageConsumer;
    private final FunctionExceptionHandler exceptionHandler;
    private final Validator validator;

    @Override
    public Message<String> apply(EmailNotificationMessage request) {
        try {
            BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(request, "notificationMessage");
            validator.validate(request, bindingResult);

            if (bindingResult.hasErrors()) {
                throw new BindException(bindingResult);
            }
            notificationMessageConsumer.send(request);
            return MessageBuilder.withPayload("").setHeader(FunctionInvoker.HTTP_STATUS_CODE, 202).build();
        } catch (MethodArgumentNotValidException ex) {
            return exceptionHandler.handleBadRequestException(ex);
        } catch (BindException ex) {
            return exceptionHandler.handleBadRequestException(ex);
        } catch (IllegalArgumentException | ConstraintViolationException ex) {
            return exceptionHandler.handleBadRequestException(ex);
        } catch (BadRequestException ex) {
            return exceptionHandler.handleBadRequestException(ex);
        } catch (ResourceNotFoundException ex) {
            return exceptionHandler.handleResourceNotFoundException(ex);
        } catch (Exception ex) {
            return exceptionHandler.handleInternalServerException(ex);
        }
    }
}
