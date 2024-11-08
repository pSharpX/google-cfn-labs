package com.onebank.taskmaster.createtask.function;

import com.onebank.taskmaster.createtask.model.CreateTaskRequest;
import com.onebank.taskmaster.createtask.service.CreateTask;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.function.adapter.gcp.FunctionInvoker;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class CreateTaskFunction implements Function<CreateTaskRequest, Message<String>> {

    private final CreateTask taskMaster;

    @Override
    public Message<String> apply(CreateTaskRequest request) {
        String taskId = taskMaster.createNewTask(request);
        return MessageBuilder.withPayload(taskId).setHeader(FunctionInvoker.HTTP_STATUS_CODE, 201).build();
    }
}
