package com.onebank.taskmaster.sendnotification.service;

import com.onebank.taskmaster.sendnotification.model.AbstractMessage;
import lombok.NonNull;

public interface MessageConsumer<T extends AbstractMessage> {
    void send(@NonNull T message);
}
