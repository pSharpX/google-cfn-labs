package com.onebank.taskmaster.createnotification.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationPreferenceEntity {
    private Long id;
    private String userIdentifier;
    @Builder.Default
    private boolean emailEnabled = true;
    @Builder.Default
    private boolean smsEnabled = true;
    @Builder.Default
    private boolean pushEnabled = true;
    @Builder.Default
    private boolean inAppEnabled = true;
    @Builder.Default
    private Boolean enabled = true;
    private String createdBy;
    private LocalDateTime createdDate;
    private String updatedBy;
    private LocalDateTime updateDatetime;
}
