package com.onebank.taskmaster.searchtask.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ToggleTaskStatusService implements ToggleTaskStatus {
    @Override
    public void toggle(@NonNull String taskId) {
        throw new UnsupportedOperationException();
    }
}
