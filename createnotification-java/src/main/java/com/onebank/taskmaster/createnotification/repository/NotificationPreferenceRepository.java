package com.onebank.taskmaster.createnotification.repository;

import com.onebank.taskmaster.createnotification.entity.NotificationPreferenceEntity;

import java.util.Optional;

public interface NotificationPreferenceRepository {
    Optional<NotificationPreferenceEntity> findByUserIdentifier(String userIdentifier);
}
