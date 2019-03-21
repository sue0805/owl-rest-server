package com.pm.mapper.test;

import java.util.Random;


public class Test {

    @org.junit.Test
    public void randomtest(){
        Random randomGenerator = new Random();
        long randomLongUniform = randomGenerator.nextLong();

        System.out.println(randomLongUniform);
    }
}
