package com.onebank.taskmaster.createnotification.repository;

import com.onebank.taskmaster.createnotification.entity.NotificationEntity;
import com.onebank.taskmaster.createnotification.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Optional;

import static com.onebank.taskmaster.createnotification.exception.utils.ExceptionConstantsUtils.NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
public class MyBatisNotificationRepository implements NotificationRepository {
    private final SqlSessionFactory sqlSessionFactory;

    @Override
    public Optional<NotificationEntity> findById(Long id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.getMapper(NotificationRepository.class).findById(id);
        }
    }

    @Override
    public NotificationEntity save(NotificationEntity entity) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            NotificationRepository repository = sqlSession.getMapper(NotificationRepository.class);
            Long id = repository.create(entity);
            return  repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND, "notification record not found"));
        }
    }

    @Override
    public Long create(NotificationEntity entity) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.getMapper(NotificationRepository.class).create(entity);
        }
    }
}
