package com.onebank.taskmaster.searchtask.service;

import com.onebank.taskmaster.searchtask.converter.ConvertTo;
import com.onebank.taskmaster.searchtask.entity.TaskEntity;
import com.onebank.taskmaster.searchtask.model.SearchTaskParam;
import com.onebank.taskmaster.searchtask.model.SearchTaskResponse;
import com.onebank.taskmaster.searchtask.model.TaskDetails;
import com.onebank.taskmaster.searchtask.repository.TaskRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SearchPersistentTaskService implements SearchTask {
	private final TaskRepository taskRepository;
	private final ConvertTo<TaskEntity, TaskDetails> converter;

	@Override
	public SearchTaskResponse search(@NonNull SearchTaskParam param) {
		return new SearchTaskResponse(taskRepository.findByTitle(param.getTitle()).stream().map(converter::convert).toList());
	}
}
