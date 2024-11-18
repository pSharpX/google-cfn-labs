package com.onebank.taskmaster.searchtask.service;

import java.util.List;

import com.google.inject.Inject;
import com.onebank.taskmaster.searchtask.config.AppConfigProperties;
import com.onebank.taskmaster.searchtask.config.ConfigProvider;
import com.onebank.taskmaster.searchtask.config.SearchTaskConfigProperties;
import com.onebank.taskmaster.searchtask.function.interceptors.Auditable;
import com.onebank.taskmaster.searchtask.function.interceptors.Validated;
import com.onebank.taskmaster.searchtask.model.SearchTaskParam;
import com.onebank.taskmaster.searchtask.model.SearchTaskResponse;
import com.onebank.taskmaster.searchtask.model.TaskDetails;
import com.onebank.taskmaster.searchtask.model.TaskStatus;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Auditable
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class SearchFakeTaskService implements SearchTask {
	private final ConfigProvider configProvider;

	@Override
	@Validated
	public SearchTaskResponse search(@NonNull SearchTaskParam param) {
		AppConfigProperties configProperties = configProvider.getConfig(AppConfigProperties.class);
		SearchTaskConfigProperties searchTaskConfigProperties = configProvider.getConfig(SearchTaskConfigProperties.class);

		TaskDetails dummyTask = new TaskDetails();
		dummyTask.setTaskTitle("Complete the create task implementation");
		dummyTask.setTaskDescription("Need to complete the implementation of create task service including Unit/Integration Test");
		dummyTask.setTags(List.of("development", "code", "sprint 2", "java"));
		dummyTask.setWeight(5);
		dummyTask.setStatus(TaskStatus.IN_PROGRESS);
		return new SearchTaskResponse(List.of(dummyTask));
	}
}
