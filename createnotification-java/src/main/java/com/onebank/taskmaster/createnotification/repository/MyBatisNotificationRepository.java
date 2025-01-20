package com.onebank.taskmaster.createnotification.repository;

import com.onebank.taskmaster.createnotification.entity.NotificationEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;

@Slf4j
@RequiredArgsConstructor
public class MyBatisNotificationRepository implements NotificationRepository {
    private final SqlSessionFactory sqlSessionFactory;

    @Override
    public NotificationEntity save(NotificationEntity entity) {
        return null;
    }
}
