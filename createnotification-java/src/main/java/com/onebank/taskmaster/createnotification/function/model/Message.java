package com.onebank.taskmaster.createnotification.function.model;

import java.util.Map;

public interface Message<T> {
    T getPayload();
    Map<String, Object> getHeaders();
}
