package com.onebank.taskmaster.sendnotification.exception;

public class ResourceNotFoundException extends AppRuntimeException {
    public ResourceNotFoundException(String errorCode, String message) {
        super(errorCode, message);
    }

    public ResourceNotFoundException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
