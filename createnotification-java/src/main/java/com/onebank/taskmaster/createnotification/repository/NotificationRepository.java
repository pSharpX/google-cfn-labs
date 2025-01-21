package com.onebank.taskmaster.createnotification.repository;

import com.onebank.taskmaster.createnotification.entity.NotificationEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

public interface NotificationRepository {

    @Select("SELECT * FROM notifications WHERE id=#{name}")
    @Results(id = "tagResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name")})
    Optional<NotificationEntity> findById(Long id);

    NotificationEntity save(NotificationEntity entity);

    @Insert("INSERT INTO notifications (userIdentifier, title, message, notificationType, channel, status, enabled) VALUES (#{userIdentifier}, #{title}, #{message}, #{notificationType}, #{channel}, #{status}, #{enabled})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    Long create(NotificationEntity entity);
}
