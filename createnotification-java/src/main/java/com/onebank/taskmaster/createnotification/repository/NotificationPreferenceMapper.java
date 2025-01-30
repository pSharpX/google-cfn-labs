package com.onebank.taskmaster.createnotification.repository;

import com.onebank.taskmaster.createnotification.entity.NotificationPreferenceEntity;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

public interface NotificationPreferenceMapper {

    @Select("SELECT * FROM notifications_preferences WHERE \"userIdentifier\"=#{userIdentifier} LIMIT 1")
    @Results(id = "notificationPreferenceResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "userIdentifier", column = "userIdentifier"),
            @Result(property = "emailEnabled", column = "emailEnabled"),
            @Result(property = "smsEnabled", column = "smsEnabled"),
            @Result(property = "pushEnabled", column = "pushEnabled"),
            @Result(property = "inAppEnabled", column = "inAppEnabled"),
            @Result(property = "enabled", column = "enabled")
    })
    Optional<NotificationPreferenceEntity> findByUserIdentifier(String userIdentifier);
}
