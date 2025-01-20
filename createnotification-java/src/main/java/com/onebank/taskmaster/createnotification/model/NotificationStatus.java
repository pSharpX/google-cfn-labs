package com.onebank.taskmaster.createnotification.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum NotificationStatus {
    CREATED("CREATED", 0, "Initial status when notification is created"),
    PENDING("PENDING", 1, "Status used for failure case"),
    SENT("SENT", 2, "Status used for successful case"),
    RECEIVED("RECEIVED", 3, "Optional status after user confirmation");

    private final String name;
    private final int order;
    private final String description;

    public static NotificationStatus getByName(@NonNull String name) {
        return Stream.of(values()).filter(notificationStatus -> notificationStatus.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public static boolean isValid(String name) {
        return Stream.of(values()).anyMatch(notificationStatus -> notificationStatus.getName().equals(name));
    }
}
