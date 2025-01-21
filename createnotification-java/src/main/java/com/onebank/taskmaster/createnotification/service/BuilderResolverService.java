package com.onebank.taskmaster.createnotification.service;


import com.google.inject.Inject;
import com.onebank.taskmaster.createnotification.model.TaskNotificationType;
import com.onebank.taskmaster.createnotification.service.builders.NotificationMessageBuilder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@Slf4j
@SuppressWarnings("rawtypes")
public class BuilderResolverService implements BuilderResolver {
    private final Map<TaskNotificationType, NotificationMessageBuilder> builders;

    @Override
    public NotificationMessageBuilder resolve(@NonNull TaskNotificationType notificationType) {
        if (builders.containsKey(notificationType)) {
            return builders.get(notificationType);
        }
        throw new UnsupportedOperationException();
    }
}
