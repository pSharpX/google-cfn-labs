package com.onebank.taskmaster.notifier.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum NotificationChannel {
    EMAIL("EMAIL"),
    PUSH("PUSH"),
    SMS("SMS"),
    IN_APP("IN_APP");

    private final String name;

    public static NotificationChannel getByName(@NonNull String name) {
        return Stream.of(values()).filter(notificationChannel -> notificationChannel.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public static boolean isValid(String name) {
        return Stream.of(values()).anyMatch(notificationChannel -> notificationChannel.getName().equals(name));
    }
}
