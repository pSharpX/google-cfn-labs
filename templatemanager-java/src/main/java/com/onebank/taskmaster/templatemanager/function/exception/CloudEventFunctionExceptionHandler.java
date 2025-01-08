package com.onebank.taskmaster.templatemanager.function.exception;

import com.onebank.taskmaster.templatemanager.exception.BadRequestException;
import com.onebank.taskmaster.templatemanager.exception.InternalServerException;
import com.onebank.taskmaster.templatemanager.exception.ResourceNotFoundException;
import com.onebank.taskmaster.templatemanager.exception.handler.AbstractExceptionHandler;
import com.onebank.taskmaster.templatemanager.exception.model.GenericErrorResponse;
import com.onebank.taskmaster.templatemanager.exception.utils.ExceptionConstantsUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CloudEventFunctionExceptionHandler extends AbstractExceptionHandler<Void> {

    public Void handleBadRequestException(Exception ex) throws BadRequestException {
        log.error("Handle Bad Request Error: {}", ex.getMessage());
        throw new BadRequestException(ExceptionConstantsUtils.BAD_REQUEST, ex.getMessage());
    }

    public Void handleBadRequestException(BadRequestException ex) throws BadRequestException {
        String errorCode = ex.getErrorCode();
        String message = ex.getMessage();
        log.error("Handle Bad Request Error Code: {}, Message: {}", errorCode, message);
        throw new BadRequestException(errorCode, message);
    }

    public Void handleInternalServerException(Exception ex) throws InternalServerException {
        log.error("Handle Internal Server Error: {}", ex.getMessage());
        GenericErrorResponse errorResponse = GenericErrorResponse.internalServer();
        if (ex instanceof InternalServerException internalServerException) {
            String errorCode = internalServerException.getErrorCode();
            String message = internalServerException.getMessage();
            errorResponse = GenericErrorResponse.build(errorCode, message);
        }
        throw new InternalServerException(errorResponse.getErrorCode(), errorResponse.getMessage());
    }

    public Void handleResourceNotFoundException(Exception ex) throws ResourceNotFoundException {
        log.error("Handle Resource Not Found Error: {}", ex.getMessage());
        throw new ResourceNotFoundException(ExceptionConstantsUtils.NOT_FOUND, ex.getMessage());
    }

}
