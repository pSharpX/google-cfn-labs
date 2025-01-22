package com.onebank.taskmaster.createnotification.repository;

import com.google.inject.Inject;
import com.onebank.taskmaster.createnotification.entity.NotificationEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class MyBatisNotificationRepository implements NotificationMapper, NotificationRepository {
    private final SqlSessionFactory sqlSessionFactory;

    @Override
    public Optional<NotificationEntity> findById(Long id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.getMapper(NotificationMapper.class).findById(id);
        }
    }

    @Override
    public NotificationEntity save(NotificationEntity entity) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            NotificationMapper repository = sqlSession.getMapper(NotificationMapper.class);
            repository.create(entity);
            sqlSession.commit();
            return  entity;
        }
    }

    @Override
    public Long create(NotificationEntity entity) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.getMapper(NotificationMapper.class).create(entity);
        }
    }
}
