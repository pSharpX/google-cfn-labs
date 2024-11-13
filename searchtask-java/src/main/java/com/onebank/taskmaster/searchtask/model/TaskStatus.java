package com.onebank.taskmaster.searchtask.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TaskStatus {
	TODO("TODO"),
	IN_PROGRESS("IN_PROGRESS"),
	DONE("DONE");
	private final String name;
}
