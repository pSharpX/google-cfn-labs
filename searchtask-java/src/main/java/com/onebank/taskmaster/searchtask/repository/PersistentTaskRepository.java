package com.onebank.taskmaster.searchtask.repository;

import com.onebank.taskmaster.searchtask.entity.TaskEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class PersistentTaskRepository implements TaskRepository {
    private final SqlSessionFactory sqlSessionFactory;

    @Override
    public List<TaskEntity> findByTitle(String title) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.getMapper(TaskRepository.class).findByTitle(title);
        }
    }

    @Override
    public List<TaskEntity> findByTitleLike(String title) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.getMapper(TaskRepository.class).findByTitleLike(title);
        }
    }
}
