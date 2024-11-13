package com.onebank.taskmaster.searchtask.service;

import java.util.UUID;

import com.onebank.taskmaster.searchtask.model.CreateTaskRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateFakeTaskService implements CreateTask {
	@Override
	public String createNewTask(@NonNull CreateTaskRequest task) {
		return UUID.randomUUID().toString();
	}
}
