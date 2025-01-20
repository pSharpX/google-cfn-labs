package com.onebank.taskmaster.createnotification.helper;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.Getter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidatorHelper {
    @Getter
    private static final Validator validator;
    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
