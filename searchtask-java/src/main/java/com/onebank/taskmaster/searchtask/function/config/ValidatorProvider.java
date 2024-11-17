package com.onebank.taskmaster.searchtask.function.config;

import com.google.inject.Provider;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class ValidatorProvider implements Provider<Validator> {
    @Override
    public Validator get() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }
}
