package com.onebank.taskmaster.createtask.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum TaskCreationStatus {
	PENDING("PENDING"),
	CREATED("CREATED"),
	DELETED("DELETED");
	private final String name;

	public static TaskCreationStatus getByName(@NonNull String name) {
		return Stream.of(values()).filter(taskCreationStatus -> taskCreationStatus.getName().equalsIgnoreCase(name))
				.findFirst()
				.orElse(null);
	}
}
