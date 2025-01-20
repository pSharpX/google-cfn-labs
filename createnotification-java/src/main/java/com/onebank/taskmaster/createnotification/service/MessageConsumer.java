package com.onebank.taskmaster.createnotification.service;

import com.onebank.taskmaster.createnotification.model.AbstractMessage;
import lombok.NonNull;

public interface MessageConsumer<T extends AbstractMessage> {
    void send(@NonNull T message);
}
