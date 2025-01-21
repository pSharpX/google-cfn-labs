package com.onebank.taskmaster.createnotification.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;

@Slf4j
@RequiredArgsConstructor
public class MyBatisNotificationPreferenceRepository implements NotificationPreferenceMapper {
    private final SqlSessionFactory sqlSessionFactory;
}
