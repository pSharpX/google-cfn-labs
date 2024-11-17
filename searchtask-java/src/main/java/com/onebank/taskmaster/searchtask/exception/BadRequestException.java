package com.onebank.taskmaster.searchtask.exception;

public class BadRequestException extends AppRuntimeException {
    public BadRequestException(String errorCode, String message) {
        super(errorCode, message);
    }

    public BadRequestException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

}
