package com.pm.mapper.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeLogAspect {

    private long startTime;
    private long endTime;
    private static final Logger logger = LoggerFactory.getLogger(TimeLogAspect.class);

    @Before("execution(* com.pm.mapper.controller.*.*(..))")
    public void beforeDBWork(JoinPoint joinPoint){
        startTime = System.currentTimeMillis();
    }

    @AfterReturning("execution(* com.pm.mapper.controller.*.*(..))")
    public void afterDBWork(JoinPoint joinPoint){
        endTime = System.currentTimeMillis();
        logger.info(joinPoint.toString() + " Time : " + (endTime - startTime));

    }
}
