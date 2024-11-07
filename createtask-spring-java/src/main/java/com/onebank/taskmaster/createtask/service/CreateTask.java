package com.onebank.taskmaster.createtask.service;

import com.onebank.taskmaster.createtask.model.CreateTaskRequest;
import lombok.NonNull;

public interface CreateTask {
	String createNewTask(@NonNull CreateTaskRequest task);
}
