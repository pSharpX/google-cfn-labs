package com.onebank.taskmaster.searchtask.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum AppIdentifier {
    CONTROL_PLANE( "Control Plane", "control-plane"),
    SCHEDULER("Scheduler","scheduler"),
    NOTIFIER("Notifier","notifier");

    private final String name;
    private final String code;

    public static AppIdentifier getByCode(@NonNull String code) {
        return Stream.of(values())
                .filter(item -> item.getCode().equals(code)).findAny().orElse(null);
    }
}
