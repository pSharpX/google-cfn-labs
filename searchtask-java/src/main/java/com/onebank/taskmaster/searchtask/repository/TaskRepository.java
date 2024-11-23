package com.onebank.taskmaster.searchtask.repository;

import com.onebank.taskmaster.searchtask.entity.TaskEntity;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TaskRepository {
    @Select("SELECT id, title, description, weight FROM tasks WHERE title = #{title}")
    @Results(id = "taskResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "description", column = "description"),
            @Result(property = "weight", column = "weight")})
    List<TaskEntity> findByTitle(String title);

    @Select("SELECT id, title, description, weight FROM tasks WHERE title ILIKE CONCAT('%', #{title}, '%')")
    @ResultMap("taskResultMap")
    List<TaskEntity> findByTitleLike(String title);
}
