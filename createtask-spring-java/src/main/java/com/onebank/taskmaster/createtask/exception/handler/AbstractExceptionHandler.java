package com.onebank.taskmaster.createtask.exception.handler;

import com.onebank.taskmaster.createtask.exception.BadRequestException;
import org.springframework.web.bind.MethodArgumentNotValidException;

public abstract class AbstractExceptionHandler<T>{
    public abstract T handleBadRequestException (Exception ex);
    public abstract T handleBadRequestException (MethodArgumentNotValidException ex);
    public abstract T handleBadRequestException (BadRequestException ex);
    public abstract T handleInternalServerException (Exception ex);
    public abstract T handleResourceNotFoundException(Exception ex);
}
