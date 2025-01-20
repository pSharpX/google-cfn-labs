package com.onebank.taskmaster.createnotification.service;

import com.onebank.taskmaster.createnotification.model.TaskNotificationType;
import com.onebank.taskmaster.createnotification.service.providers.MessageContentProvider;
import lombok.NonNull;

@SuppressWarnings("rawtypes")
public interface ContentProviderResolver {
    MessageContentProvider resolve(@NonNull TaskNotificationType type);
}
