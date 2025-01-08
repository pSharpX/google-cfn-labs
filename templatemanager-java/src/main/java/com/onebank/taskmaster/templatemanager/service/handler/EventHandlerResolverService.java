package com.onebank.taskmaster.templatemanager.service.handler;

import com.google.inject.Inject;
import com.onebank.taskmaster.templatemanager.model.EventType;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class EventHandlerResolverService implements EventHandlerResolver {
    private final PubSubEventHandler pubSubEventHandler;
    private final StorageEventHandler storageEventHandler;

    @Override
    public EventHandler resolve(@NonNull EventType type) {
        return switch (type) {
            case STORAGE -> storageEventHandler;
            case PUBSUB -> pubSubEventHandler;
            default -> throw new UnsupportedOperationException();
        };
    }
}
