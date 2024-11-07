package com.onebank.taskmaster.createtask.function;

import com.onebank.taskmaster.createtask.model.CreateTaskRequest;
import com.onebank.taskmaster.createtask.service.CreateTask;
import org.springframework.cloud.function.adapter.gcp.FunctionInvoker;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CreateTaskFunction {

    @Bean
    public Function<CreateTaskRequest, Message<String>> entry(CreateTask taskMaster) {
        return request -> MessageBuilder.withPayload(taskMaster.createNewTask(request)).setHeader(FunctionInvoker.HTTP_STATUS_CODE, 201).build();
    }
}
