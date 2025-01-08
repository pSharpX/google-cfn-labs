package com.onebank.taskmaster.templatemanager.function.exception;

import com.onebank.taskmaster.templatemanager.exception.BadRequestException;
import com.onebank.taskmaster.templatemanager.exception.InternalServerException;
import com.onebank.taskmaster.templatemanager.exception.handler.AbstractExceptionHandler;
import com.onebank.taskmaster.templatemanager.exception.model.GenericErrorResponse;
import com.onebank.taskmaster.templatemanager.exception.utils.ExceptionConstantsUtils;
import com.onebank.taskmaster.templatemanager.function.model.Message;
import com.onebank.taskmaster.templatemanager.function.model.MessageBuilder;
import com.onebank.taskmaster.templatemanager.helper.FunctionUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class FunctionExceptionHandler extends AbstractExceptionHandler<Message<String>> {

    private String serializeErrorResponse (@NonNull GenericErrorResponse errorResponse) {
        try {
            return FunctionUtils.toJson(errorResponse);
        } catch (IOException e) {
            log.error("Error occurred during error response serialization: {}", e.getMessage());
            return ExceptionConstantsUtils.INTERNAL_SERVER_ERROR_MESSAGE;
        }
    }

    public Message<String> handleBadRequestException(Exception ex) {
        log.error("Handle Bad Request Error: {}", ex.getMessage());
        String error = serializeErrorResponse(GenericErrorResponse.badRequest(ex.getMessage()));
        return MessageBuilder
                .withPayload(error)
                .setHeader(FunctionUtils.HTTP_STATUS_CODE, 400)
                .build();
    }

    public Message<String> handleBadRequestException(BadRequestException ex) {
        String errorCode = ex.getErrorCode();
        String message = ex.getMessage();
        log.error("Handle Bad Request Error Code: {}, Message: {}", errorCode, message);
        String error = serializeErrorResponse(GenericErrorResponse.badRequest(errorCode, message));
        return MessageBuilder
                .withPayload(error)
                .setHeader(FunctionUtils.HTTP_STATUS_CODE, 400)
                .build();
    }

    public Message<String> handleInternalServerException(Exception ex) {
        log.error("Handle Internal Server Error: {}", ex.getMessage());
        GenericErrorResponse errorResponse = GenericErrorResponse.internalServer();
        if (ex instanceof InternalServerException internalServerException) {
            String errorCode = internalServerException.getErrorCode();
            String message = internalServerException.getMessage();
            errorResponse = GenericErrorResponse.build(errorCode, message);
        }
        String error = serializeErrorResponse(errorResponse);
        return MessageBuilder
                .withPayload(error)
                .setHeader(FunctionUtils.HTTP_STATUS_CODE, 500)
                .build();
    }

    public Message<String> handleResourceNotFoundException(Exception ex) {
        log.error("Handle Resource Not Found Error: {}", ex.getMessage());
        String error = serializeErrorResponse(GenericErrorResponse.notFound(ex.getMessage()));
        return MessageBuilder
                .withPayload(error)
                .setHeader(FunctionUtils.HTTP_STATUS_CODE, 404)
                .build();
    }

}
