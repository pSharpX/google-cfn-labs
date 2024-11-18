package com.onebank.taskmaster.searchtask.service;

import com.google.inject.Inject;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class ToggleTaskStatusService implements ToggleTaskStatus {
    @Override
    public void toggle(@NonNull String taskId) {
        throw new UnsupportedOperationException();
    }
}
