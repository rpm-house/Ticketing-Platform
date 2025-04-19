package com.company.common.config.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class ServiceLoggingAspect {

    @Around("execution(com.company.service.*(..))")
    public Object logServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        System.out.println("Executing: " + methodName);
        try {
            Object result = joinPoint.proceed();
            log.info("Completed: " + methodName);
            return result;
        } catch (Exception ex) {
        	log.error("Exception in: " + methodName + ", " + ex.getMessage());
            throw ex;
        }
    }
}