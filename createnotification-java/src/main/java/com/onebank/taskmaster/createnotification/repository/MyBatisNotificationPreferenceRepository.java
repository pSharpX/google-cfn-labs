package com.onebank.taskmaster.createnotification.repository;

import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class MyBatisNotificationPreferenceRepository implements NotificationPreferenceRepository {
    private final SqlSessionFactory sqlSessionFactory;
}
