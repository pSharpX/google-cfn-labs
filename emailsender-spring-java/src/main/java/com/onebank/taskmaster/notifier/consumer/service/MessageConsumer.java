package com.onebank.taskmaster.notifier.consumer.service;

import com.onebank.taskmaster.notifier.model.AbstractMessage;
import lombok.NonNull;

public interface MessageConsumer<T extends AbstractMessage> {
    void send(@NonNull T message);
}
