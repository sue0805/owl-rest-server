package com.pm.mapper.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Slf4j
public class TimeLogAspectTest {
//
//    private long startTime;
//    private long endTime;
//    private static final Logger logger = LoggerFactory.getLogger(TimeLogAspect.class);
//
//    @Autowired
//    private TimeLogAspect timeLogAspect;
//
//    @Test
//    public void testTimeLogAspect_NotNull(){
//        Assert.assertNotNull(timeLogAspect);
//    }
//
//    @Test
//    @Before("execution(* com.pm.mapper.controller.*.*.*(..))")
//    public void testBeforeDBWork(){
//        startTime = System.currentTimeMillis();
//        Assert.assertNotNull(String.valueOf(startTime));
//    }
//
//    @Test
//    @AfterReturning("execution(* com.pm.mapper.controller.*.*.*(..))")
//    public void afterDBWork(){
//        endTime = System.currentTimeMillis();
//        logger.info(String.valueOf(endTime));
//        Assert.assertNotNull("Time : " + (endTime - startTime));
//    }
//
}
