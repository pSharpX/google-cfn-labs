package com.onebank.taskmaster.createnotification.model.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = TaskNotificationTypeValidator.class)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface TaskNotificationTypeCode {
    String message() default "TaskNotificationType code must be valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
