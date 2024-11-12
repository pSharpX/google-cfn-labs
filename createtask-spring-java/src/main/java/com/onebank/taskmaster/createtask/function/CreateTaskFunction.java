package com.onebank.taskmaster.createtask.function;

import com.onebank.taskmaster.createtask.exception.BadRequestException;
import com.onebank.taskmaster.createtask.exception.ResourceNotFoundException;
import com.onebank.taskmaster.createtask.function.exception.FunctionExceptionHandler;
import com.onebank.taskmaster.createtask.model.CreateTaskRequest;
import com.onebank.taskmaster.createtask.service.CreateTask;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.function.adapter.gcp.FunctionInvoker;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.Validator;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class CreateTaskFunction implements Function<CreateTaskRequest, Message<String>> {

    private final FunctionExceptionHandler exceptionHandler;
    private final CreateTask taskMaster;
    private final Validator validator;

    @Override
    public Message<String> apply(CreateTaskRequest request) {
        try {
            BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(request, "createTaskRequest");
            validator.validate(request, bindingResult);

            if (bindingResult.hasErrors()) {
                throw new BindException(bindingResult);
            }
            String taskId = taskMaster.createNewTask(request);
            return MessageBuilder.withPayload(taskId).setHeader(FunctionInvoker.HTTP_STATUS_CODE, 201).build();
        } catch (MethodArgumentNotValidException ex) {
            return exceptionHandler.handleBadRequestException(ex);
        } catch (BindException ex) {
            return exceptionHandler.handleBadRequestException(ex);
        } catch (IllegalArgumentException | ConstraintViolationException ex) {
            return exceptionHandler.handleBadRequestException(ex);
        } catch (BadRequestException ex) {
            return exceptionHandler.handleBadRequestException(ex);
        } catch (ResourceNotFoundException ex) {
            return exceptionHandler.handleResourceNotFoundException(ex);
        } catch (Exception ex) {
            return exceptionHandler.handleInternalServerException(ex);
        }
    }
}
