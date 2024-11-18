package com.onebank.taskmaster.searchtask.function.interceptors.validator;

import com.google.inject.Inject;
import com.onebank.taskmaster.searchtask.helper.ValidatorHelper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class ValidatorInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Validator validator = ValidatorHelper.getValidator();
        for (Object arg : invocation.getArguments()) {
            if (arg != null) {
                Set<ConstraintViolation<Object>> violations = validator.validate(arg);
                if (!violations.isEmpty()) {
                    StringBuilder errorMessage = new StringBuilder();
                    for (ConstraintViolation<Object> violation : violations) {
                        errorMessage.append(String.format("%s %s", violation.getPropertyPath().toString(), violation.getMessage())).append("; ");
                    }
                    throw new IllegalArgumentException(errorMessage.toString());
                }
            }
        }
        return invocation.proceed();
    }
}
