package com.onebank.taskmaster.searchtask.repository;

import com.onebank.taskmaster.searchtask.entity.TagEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class PersistentTagRepository implements TagRepository {
    private final SqlSessionFactory sqlSessionFactory;

    @Override
    public Optional<TagEntity> findByName(String name) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.getMapper(TagRepository.class).findByName(name);
        }
    }
}
