package com.onebank.taskmaster.templatemanager.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum SubEventType {
    DOCUMENT_CREATED(EventType.FIRESTORE, "created", "google.cloud.firestore.document.v1.created"),
    DOCUMENT_DELETED(EventType.FIRESTORE, "deleted", "google.cloud.firestore.document.v1.deleted"),
    DOCUMENT_UPDATED(EventType.FIRESTORE, "updated", "google.cloud.firestore.document.v1.updated"),
    DOCUMENT_WRITTEN(EventType.FIRESTORE, "written", "google.cloud.firestore.document.v1.written"),
    MESSAGE_PUBLISHED(EventType.PUBSUB, "messagePublished", "google.cloud.pubsub.topic.v1.messagePublished"),
    OBJECT_FINALIZED(EventType.STORAGE, "finalized", "google.cloud.storage.object.v1.finalized"),
    OBJECT_DELETED(EventType.STORAGE, "deleted", "google.cloud.storage.object.v1.deleted");

    private final EventType type;
    private final String name;
    private final String fullName;

    public static SubEventType getByName(@NonNull String name) {
        return Stream.of(values()).filter(eventType -> eventType.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public static boolean isValid(String name) {
        return Stream.of(values()).anyMatch(eventType -> eventType.getName().equals(name));
    }
}
