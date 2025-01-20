package com.onebank.taskmaster.createnotification.service.producers;

import com.onebank.taskmaster.createnotification.model.AbstractMessage;

public interface MessageProducer<T extends AbstractMessage> {
    void sendMessage(T message);
}
