package com.onebank.taskmaster.templatemanager.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum EventType {
    STORAGE("google.cloud.storage.object.v1"),
    PUBSUB("google.cloud.pubsub.topic.v1"),
    FIRESTORE("google.cloud.firestore.document.v1");

    private final String prefix;

    public static EventType getByPrefix(@NonNull String name) {
        return Stream.of(values()).filter(eventType -> name.startsWith(eventType.prefix))
                .findFirst()
                .orElse(null);
    }

    public static boolean isValid(String name) {
        return Stream.of(values()).anyMatch(eventType -> eventType.getPrefix().equals(name));
    }
}
