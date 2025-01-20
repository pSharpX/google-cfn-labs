package com.onebank.taskmaster.createnotification.model.validators;

import com.onebank.taskmaster.createnotification.model.TaskNotificationType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class TaskNotificationTypeValidator implements ConstraintValidator<TaskNotificationTypeCode, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(Objects.isNull(value)) {
            log.debug("The value for TaskNotificationType is null. Skipping validation...");
            return true;
        }

        return TaskNotificationType.isValid(value);
    }
}
