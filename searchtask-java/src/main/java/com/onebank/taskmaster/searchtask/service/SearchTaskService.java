package com.onebank.taskmaster.searchtask.service;

import com.google.inject.Inject;
import com.onebank.taskmaster.searchtask.converter.TaskDetailsConverter;
import com.onebank.taskmaster.searchtask.function.interceptors.Auditable;
import com.onebank.taskmaster.searchtask.function.interceptors.Validated;
import com.onebank.taskmaster.searchtask.model.SearchTaskParam;
import com.onebank.taskmaster.searchtask.model.SearchTaskResponse;
import com.onebank.taskmaster.searchtask.repository.TaskRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Auditable
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class SearchTaskService implements SearchTask {
	private final TaskRepository taskRepository;
	private final TaskDetailsConverter converter;

	@Override
	@Validated
	public SearchTaskResponse search(@NonNull SearchTaskParam param) {
		return new SearchTaskResponse(taskRepository.findByTitleLike(param.getTitle()).stream().map(converter::convert).toList());
	}
}
