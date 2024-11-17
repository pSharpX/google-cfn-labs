package com.onebank.taskmaster.searchtask.exception.handler;

import com.onebank.taskmaster.searchtask.exception.BadRequestException;

public abstract class AbstractExceptionHandler<T>{
    public abstract T handleBadRequestException (Exception ex);
    public abstract T handleBadRequestException (BadRequestException ex);
    public abstract T handleInternalServerException (Exception ex);
    public abstract T handleResourceNotFoundException(Exception ex);
}
