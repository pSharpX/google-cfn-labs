package com.onebank.taskmaster.createnotification.function.interceptors.logger;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
@RequiredArgsConstructor
public class LoggerInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long startTime = System.currentTimeMillis();

        try {
            return invocation.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            log.debug("Execute {}#{} start={} end={} duration={} ms.", invocation.getMethod().getDeclaringClass().getName(), invocation.getMethod().getName(), startTime, endTime, (endTime - startTime));
        }
    }
}
