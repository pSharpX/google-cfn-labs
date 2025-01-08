package com.onebank.taskmaster.templatemanager.function.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class MessageHeaderAccessor {
    private final Map<String, Object> headers;

    public MessageHeaderAccessor() {
        this(null);
    }

    public MessageHeaderAccessor(Message<?> message) {
        this.headers = Objects.nonNull(message) ? message.getHeaders(): new ConcurrentHashMap<>();
    }

    protected MessageHeaderAccessor createAccessor(Message<?> message) {
        return new MessageHeaderAccessor(message);
    }

    /**
     * Retrieve the value for the header with the given name.
     * @param headerName the name of the header
     * @return the associated value, or {@code null} if none found
     */
    public Object getHeader(String headerName) {
        return this.headers.get(headerName);
    }

    /**
     * Set the value for the given header name.
     * <p>If the provided value is {@code null}, the header will be removed.
     */
    public void setHeader(String name, Object value) {
        if (value != null) {
            // Modify header if necessary
            if (!(value == getHeader(name))) {
                this.headers.put(name, value);
            }
        }
        else {
            // Remove header if available
            this.headers.remove(name);
        }
    }

    /**
     * Set the value for the given header name only if the header name is not
     * already associated with a value.
     */
    public void setHeaderIfAbsent(String name, Object value) {
        if (getHeader(name) == null) {
            setHeader(name, value);
        }
    }

    /**
     * Remove the value for the given header name.
     */
    public void removeHeader(String headerName) {
        if (Objects.nonNull(headerName) && !headerName.isEmpty()) {
            setHeader(headerName, null);
        }
    }

    /**
     * Return a copy of the underlying header values as a plain {@link Map} object.
     * <p>This method can be invoked many times, with modifications in between
     * where each new call returns a fresh copy of the current header values.
     */
    public Map<String, Object> toMap() {
        return new HashMap<>(this.headers);
    }
}
