package com.onebank.taskmaster.searchtask.exception;

public class InternalServerException extends AppRuntimeException {
    public InternalServerException(String errorCode, String message) {
        super(errorCode, message);
    }

    public InternalServerException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

}
