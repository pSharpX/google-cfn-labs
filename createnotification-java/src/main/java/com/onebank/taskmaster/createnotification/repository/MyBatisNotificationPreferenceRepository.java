package com.onebank.taskmaster.createnotification.repository;

import com.google.inject.Inject;
import com.onebank.taskmaster.createnotification.entity.NotificationPreferenceEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class MyBatisNotificationPreferenceRepository implements NotificationPreferenceMapper, NotificationPreferenceRepository {
    private final SqlSessionFactory sqlSessionFactory;

    @Override
    public Optional<NotificationPreferenceEntity> findByUserIdentifier(String userIdentifier) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.getMapper(NotificationPreferenceMapper.class).findByUserIdentifier(userIdentifier);
        }
    }
}
