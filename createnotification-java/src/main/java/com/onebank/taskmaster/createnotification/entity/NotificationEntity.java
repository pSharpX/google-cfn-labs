package com.onebank.taskmaster.createnotification.entity;

import com.onebank.taskmaster.createnotification.model.NotificationChannel;
import com.onebank.taskmaster.createnotification.model.NotificationStatus;
import com.onebank.taskmaster.createnotification.model.TaskNotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEntity {
    private Long id;
    private String userIdentifier;
    private String title;
    private String message;
    private TaskNotificationType notificationType;
    @Builder.Default
    private NotificationChannel channel = NotificationChannel.EMAIL;
    @Builder.Default
    private NotificationStatus status = NotificationStatus.CREATED;
    @Builder.Default
    private Boolean enabled = true;
    private String createdBy;
    private LocalDateTime createdDate;
    private String updatedBy;
    private LocalDateTime updateDatetime;
}
