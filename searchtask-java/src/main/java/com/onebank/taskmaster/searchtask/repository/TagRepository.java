package com.onebank.taskmaster.searchtask.repository;

import com.onebank.taskmaster.searchtask.entity.TagEntity;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

public interface TagRepository {
    @Select("SELECT * FROM tags WHERE name=#{name}")
    @Results(id = "tagResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name")})
    Optional<TagEntity> findByName(String name);
}
