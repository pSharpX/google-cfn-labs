package com.onebank.taskmaster.templatemanager.exception;

import lombok.Getter;

@Getter
public class AppRuntimeException extends RuntimeException {
    private final String errorCode;

    protected AppRuntimeException(String errorCode, String message) {
        this(errorCode, message, null);
    }

    protected AppRuntimeException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}