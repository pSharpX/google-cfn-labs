package com.onebank.taskmaster.sendnotification.exception.handler;

import com.onebank.taskmaster.sendnotification.exception.BadRequestException;

public abstract class AbstractExceptionHandler<T>{
    public abstract T handleBadRequestException (Exception ex);
    public abstract T handleBadRequestException (BadRequestException ex);
    public abstract T handleInternalServerException (Exception ex);
    public abstract T handleResourceNotFoundException(Exception ex);
}
