package com.onebank.taskmaster.createnotification.repository;

import com.onebank.taskmaster.createnotification.entity.NotificationEntity;

public interface NotificationRepository {
    NotificationEntity save(NotificationEntity entity);
}
