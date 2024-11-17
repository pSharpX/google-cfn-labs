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
        log.info("Start method's arguments validation");
        for (Object arg : invocation.getArguments()) {
            if (arg != null) {
                Set<ConstraintViolation<Object>> violations = validator.validate(arg);
                if (!violations.isEmpty()) {
                    StringBuilder errorMessage = new StringBuilder("Validation failed: ");
                    for (ConstraintViolation<Object> violation : violations) {
                        errorMessage.append(violation.getMessage()).append("; ");
                    }
                    throw new IllegalArgumentException(errorMessage.toString());
                }
            }
        }
        return invocation.proceed();
    }
}
