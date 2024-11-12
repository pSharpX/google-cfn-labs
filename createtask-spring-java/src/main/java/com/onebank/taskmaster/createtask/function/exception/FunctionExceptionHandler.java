package com.onebank.taskmaster.createtask.function.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onebank.taskmaster.createtask.exception.BadRequestException;
import com.onebank.taskmaster.createtask.exception.InternalServerException;
import com.onebank.taskmaster.createtask.exception.ResourceNotFoundException;
import com.onebank.taskmaster.createtask.exception.handler.AbstractExceptionHandler;
import com.onebank.taskmaster.createtask.exception.model.GenericErrorResponse;
import com.onebank.taskmaster.createtask.exception.utils.ExceptionConstantsUtils;
import jakarta.validation.ConstraintViolationException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.cloud.function.adapter.gcp.FunctionInvoker;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class FunctionExceptionHandler extends AbstractExceptionHandler<Message<String>> {

    private final ObjectMapper objectMapper;

    private String serializeErrorResponse (@NonNull GenericErrorResponse errorResponse) {
        try {
            return this.objectMapper.writeValueAsString(errorResponse);
        } catch (JsonProcessingException e) {
            log.error("Error occurred during error response serialization: {}", e.getMessage());
            return ExceptionConstantsUtils.INTERNAL_SERVER_ERROR_MESSAGE;
        }
    }

    @ExceptionHandler({
            IllegalArgumentException.class,
            ConstraintViolationException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Message<String> handleBadRequestException(Exception ex) {
        log.error("Handle Bad Request Error: {}", ExceptionUtils.getStackTrace(ex));
        String error = serializeErrorResponse(GenericErrorResponse.badRequest(ex.getMessage()));
        return MessageBuilder
                .withPayload(error)
                .setHeader(FunctionInvoker.HTTP_STATUS_CODE, 400)
                .build();
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Message<String> handleBadRequestException(BindException ex) {
        String message = ex.getBindingResult().getFieldErrors()
                .stream().map(fieldError -> Optional.ofNullable(fieldError.getDefaultMessage())
                        .orElse(ExceptionConstantsUtils.BAD_REQUEST_ERROR_MESSAGE))
                .findFirst().orElse(ex.getMessage());
        String error = serializeErrorResponse(GenericErrorResponse.badRequest(message));
        return MessageBuilder
                .withPayload(error)
                .setHeader(FunctionInvoker.HTTP_STATUS_CODE, 400)
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Message<String> handleBadRequestException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors()
                .stream().map(fieldError -> Optional.ofNullable(fieldError.getDefaultMessage())
                        .orElse(ExceptionConstantsUtils.BAD_REQUEST_ERROR_MESSAGE))
                .findFirst().orElse(ex.getMessage());
        String error = serializeErrorResponse(GenericErrorResponse.badRequest(message));
        return MessageBuilder
                .withPayload(error)
                .setHeader(FunctionInvoker.HTTP_STATUS_CODE, 400)
                .build();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Message<String> handleBadRequestException(BadRequestException ex) {
        String errorCode = ex.getErrorCode();
        String message = ex.getMessage();
        log.error("Handle Bad Request Error Code: {}, Message: {}", errorCode, message);
        String error = serializeErrorResponse(GenericErrorResponse.badRequest(errorCode, message));
        return MessageBuilder
                .withPayload(error)
                .setHeader(FunctionInvoker.HTTP_STATUS_CODE, 400)
                .build();
    }

    @ExceptionHandler({
            InternalServerException.class,
            NullPointerException.class,
            Exception.class,
    })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Message<String> handleInternalServerException(Exception ex) {
        log.error("Handle Internal Server Error: {}", ExceptionUtils.getStackTrace(ex));
        GenericErrorResponse errorResponse = GenericErrorResponse.internalServer();
        if (ex instanceof InternalServerException internalServerException) {
            String errorCode = internalServerException.getErrorCode();
            String message = internalServerException.getMessage();
            errorResponse = GenericErrorResponse.build(errorCode, message);
        }
        String error = serializeErrorResponse(errorResponse);
        return MessageBuilder
                .withPayload(error)
                .setHeader(FunctionInvoker.HTTP_STATUS_CODE, 500)
                .build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Message<String> handleResourceNotFoundException(Exception ex) {
        log.error("Handle Resource Not Found Error: {}", ExceptionUtils.getStackTrace(ex));
        String error = serializeErrorResponse(GenericErrorResponse.notFound(ex.getMessage()));
        return MessageBuilder
                .withPayload(error)
                .setHeader(FunctionInvoker.HTTP_STATUS_CODE, 404)
                .build();
    }

}
