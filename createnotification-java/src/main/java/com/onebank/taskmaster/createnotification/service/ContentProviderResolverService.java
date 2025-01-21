package com.onebank.taskmaster.createnotification.service;

import com.google.inject.Inject;
import com.onebank.taskmaster.createnotification.model.TaskNotificationType;
import com.onebank.taskmaster.createnotification.service.providers.MessageContentProvider;
import com.onebank.taskmaster.createnotification.service.providers.TaskMessageContentProvider;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@Slf4j
@SuppressWarnings("rawtypes")
public class ContentProviderResolverService implements ContentProviderResolver {
    private final Map<TaskNotificationType, TaskMessageContentProvider> providers;

    @Override
    public MessageContentProvider resolve(@NonNull TaskNotificationType type) {
        if (providers.containsKey(type)) {
            return providers.get(type);
        }
        throw new UnsupportedOperationException();
    }
}
