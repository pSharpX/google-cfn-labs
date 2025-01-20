package com.onebank.taskmaster.createnotification.model.validators;

import com.onebank.taskmaster.createnotification.model.NotificationChannel;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class NotificationChannelValidator implements ConstraintValidator<NotificationChannelCode, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(Objects.isNull(value)) {
            log.debug("The value for NotificationChannel is null. Skipping validation...");
            return true;
        }

        return NotificationChannel.isValid(value);
    }
}
