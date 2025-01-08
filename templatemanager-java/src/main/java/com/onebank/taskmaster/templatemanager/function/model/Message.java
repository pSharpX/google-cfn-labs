package com.onebank.taskmaster.templatemanager.function.model;

import java.util.Map;

public interface Message<T> {
    T getPayload();
    Map<String, Object> getHeaders();
}
