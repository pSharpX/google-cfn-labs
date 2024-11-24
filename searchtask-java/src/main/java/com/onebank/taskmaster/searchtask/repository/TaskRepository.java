package com.onebank.taskmaster.searchtask.repository;

import com.onebank.taskmaster.searchtask.entity.TaskEntity;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Set;

public interface TaskRepository {
    @Select("SELECT id, title, description, weight FROM tasks WHERE title = #{title}")
    @Results(id = "taskResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "description", column = "description"),
            @Result(property = "weight", column = "weight"),
            @Result(property = "tags", column = "id", javaType = Set.class, many = @Many(
                    select = "com.onebank.taskmaster.searchtask.repository.TagRepository.findByTaskId",
                    fetchType = FetchType.LAZY
            ))
    })
    List<TaskEntity> findByTitle(String title);

    @Select("SELECT id, title, description, weight FROM tasks WHERE title ILIKE CONCAT('%', #{title}, '%')")
    @ResultMap("taskResultMap")
    List<TaskEntity> findByTitleLike(String title);
}
