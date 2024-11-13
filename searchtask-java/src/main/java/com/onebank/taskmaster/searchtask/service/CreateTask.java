package com.onebank.taskmaster.searchtask.service;

import com.onebank.taskmaster.searchtask.model.CreateTaskRequest;
import lombok.NonNull;

public interface CreateTask {
	String createNewTask(@NonNull CreateTaskRequest task);
}
