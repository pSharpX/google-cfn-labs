package com.onebank.taskmaster.searchtask.function.interceptors.logger;

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
        log.info("Method {}.{}  started at {} ", invocation.getMethod().getDeclaringClass().getName(), invocation.getMethod().getName(), startTime);

        try {
            return invocation.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            log.info("Method {}.{} ended at {}", invocation.getMethod().getDeclaringClass().getName(), invocation.getMethod().getName(), endTime);
            log.info("Execution time: {} ms", (endTime - startTime));
        }
    }
}
