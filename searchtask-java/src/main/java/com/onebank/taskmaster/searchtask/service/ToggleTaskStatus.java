package com.onebank.taskmaster.searchtask.service;

import lombok.NonNull;

public interface ToggleTaskStatus {
    void toggle(@NonNull String taskId);
}
