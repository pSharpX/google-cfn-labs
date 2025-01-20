package com.onebank.taskmaster.createnotification.function.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenericMessage<T> implements Message<T>, Serializable {

    private T payload;
    private Map<String, Object> headers = new ConcurrentHashMap<>();

    @Override
    public T getPayload() {
        return this.payload;
    }

    @Override
    public Map<String, Object> getHeaders() {
        return headers;
    }
}
