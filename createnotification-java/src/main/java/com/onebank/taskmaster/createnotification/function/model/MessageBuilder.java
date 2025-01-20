package com.onebank.taskmaster.createnotification.function.model;

import java.util.Map;
import java.util.Objects;

public final class MessageBuilder<T> {

    private final T payload;

    private final Message<T> providedMessage;

    private MessageHeaderAccessor headerAccessor;

    private MessageBuilder(Message<T> providedMessage) {
        Objects.requireNonNull(providedMessage, "Message must not be null");
        this.payload = providedMessage.getPayload();
        this.providedMessage = providedMessage;
        this.headerAccessor = new MessageHeaderAccessor(providedMessage);
    }

    private MessageBuilder(T payload, MessageHeaderAccessor accessor) {
        Objects.requireNonNull(payload, "Payload must not be null");
        Objects.requireNonNull(accessor, "MessageHeaderAccessor must not be null");
        this.payload = payload;
        this.providedMessage = null;
        this.headerAccessor = accessor;
    }

    /**
     * Set the message headers to use by providing a {@code MessageHeaderAccessor}.
     * @param accessor the headers to use
     */
    public MessageBuilder<T> setHeaders(MessageHeaderAccessor accessor) {
        Objects.requireNonNull(accessor, "MessageHeaderAccessor must not be null");
        this.headerAccessor = accessor;
        return this;
    }

    /**
     * Set the value for the given header name. If the provided value is {@code null},
     * the header will be removed.
     */
    public MessageBuilder<T> setHeader(String headerName, Object headerValue) {
        this.headerAccessor.setHeader(headerName, headerValue);
        return this;
    }

    /**
     * Set the value for the given header name only if the header name is not already
     * associated with a value.
     */
    public MessageBuilder<T> setHeaderIfAbsent(String headerName, Object headerValue) {
        this.headerAccessor.setHeaderIfAbsent(headerName, headerValue);
        return this;
    }

    /**
     * Remove the value for the given header name.
     */
    public MessageBuilder<T> removeHeader(String headerName) {
        this.headerAccessor.removeHeader(headerName);
        return this;
    }

    @SuppressWarnings("unchecked")
    public Message<T> build() {
        if (this.providedMessage != null) {
            return this.providedMessage;
        }
        Map<String, Object> headersToUse = this.headerAccessor.toMap();
        return new GenericMessage<>(this.payload, headersToUse);
    }

    /**
     * Create a builder for a new {@link Message} instance pre-populated with all the
     * headers copied from the provided message. The payload of the provided Message will
     * also be used as the payload for the new message.
     * <p>If the provided message is an {@link ErrorMessage}, the
     * {@link ErrorMessage#getOriginalMessage() originalMessage} it contains, will be
     * passed on to new instance.
     * @param message the Message from which the payload and all headers will be copied
     */
    public static <T> MessageBuilder<T> fromMessage(Message<T> message) {
        return new MessageBuilder<>(message);
    }

    /**
     * Create a new builder for a message with the given payload.
     * @param payload the payload
     */
    public static <T> MessageBuilder<T> withPayload(T payload) {
        return new MessageBuilder<>(payload, new MessageHeaderAccessor());
    }

    /**
     * A shortcut factory method for creating a message with the given payload
     * and {@code MessageHeaders}.
     * <p><strong>Note:</strong> the given {@code MessageHeaders} instance is used
     * directly in the new message, i.e. it is not copied.
     * @param payload the payload to use (never {@code null})
     * @param messageHeaders the headers to use (never {@code null})
     * @return the created message
     * @since 4.1
     */
    @SuppressWarnings("unchecked")
    public static <T> Message<T> createMessage(T payload, Map<String, Object> messageHeaders) {
        Objects.requireNonNull(payload, "Payload must not be null");
        Objects.requireNonNull(messageHeaders, "MessageHeaders must not be null");
        return new GenericMessage<>(payload, messageHeaders);
    }
}

