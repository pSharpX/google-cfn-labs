package com.onebank.taskmaster.templatemanager.service.handler;

import com.onebank.taskmaster.templatemanager.model.EventType;
import lombok.NonNull;

public interface EventHandlerResolver {
    EventHandler resolve(@NonNull EventType type);
}
