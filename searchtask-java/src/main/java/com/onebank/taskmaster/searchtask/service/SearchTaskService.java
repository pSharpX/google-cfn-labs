package com.onebank.taskmaster.searchtask.service;

import java.util.List;

import com.onebank.taskmaster.searchtask.model.SearchTaskParam;
import com.onebank.taskmaster.searchtask.model.SearchTaskResponse;
import com.onebank.taskmaster.searchtask.model.TaskDetails;
import com.onebank.taskmaster.searchtask.model.TaskStatus;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SearchTaskService implements SearchTask {

	@Override
	public SearchTaskResponse search(@NonNull SearchTaskParam param) {
		TaskDetails dummyTask = new TaskDetails();
		dummyTask.setTaskTitle("Complete the create task implementation");
		dummyTask.setTaskDescription("Need to complete the implementation of create task service including Unit/Integration Test");
		dummyTask.setTags(List.of("development", "code", "sprint 2", "java"));
		dummyTask.setWeight(5);
		dummyTask.setStatus(TaskStatus.IN_PROGRESS);
		return new SearchTaskResponse(List.of(dummyTask));
	}
}
