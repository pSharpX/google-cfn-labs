package com.onebank.taskmaster.notifier.model.senders;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.onebank.taskmaster.notifier.model.AbstractMessage;
import com.onebank.taskmaster.notifier.model.NotificationChannel;
import com.onebank.taskmaster.notifier.model.TaskNotificationType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "channel",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = EmailNotificationMessage.class, names = "EMAIL"),
        @JsonSubTypes.Type(value = PushNotificationMessage.class, name = "PUSH"),
        @JsonSubTypes.Type(value = SmsNotificationMessage.class, names = "SMS"),
        @JsonSubTypes.Type(value = InAppNotificationMessage.class, names = "IN_APP")
})
public abstract class NotificationMessage extends AbstractMessage {
    private Long id;
    @Setter(AccessLevel.NONE)
    private final NotificationChannel channel;
    private TaskNotificationType type;
    private String user;
    private String title;
    private String message;
}
