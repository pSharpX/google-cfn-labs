package com.onebank.taskmaster.templatemanager.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum ActionTemplateType {
    CREATE_OR_UPDATE("CREATE_OR_UPDATE", "Notification trigger when template creation is requested"),
    DELETE("DELETE", "Notification trigger when template deletion is requested");

    private final String name;
    private final String description;

    public static ActionTemplateType getByName(@NonNull String name) {
        return Stream.of(values()).filter(actionTemplateType -> actionTemplateType.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public static boolean isValid(String name) {
        return Stream.of(values()).anyMatch(actionTemplateType -> actionTemplateType.getName().equals(name));
    }
}
