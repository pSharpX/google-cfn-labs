package com.onebank.taskmaster.searchtask.model;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskRequest {
	private String taskTitle;
	private String taskDescription;
	private List<String> tags;
	private Integer weight = 0;
	@Setter(AccessLevel.NONE)
	private TaskCreationStatus status = TaskCreationStatus.PENDING;
}
