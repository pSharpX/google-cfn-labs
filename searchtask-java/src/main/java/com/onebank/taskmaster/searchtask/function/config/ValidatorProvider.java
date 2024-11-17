package com.onebank.taskmaster.searchtask.function.config;

import com.google.inject.Provides;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class ValidatorProvider {
    @Provides
    public Validator validatorGenerator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }
}
