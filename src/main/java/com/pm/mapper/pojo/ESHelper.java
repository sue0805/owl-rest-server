package com.pm.mapper.pojo;

import java.util.Random;

public class ESHelper {

    Random randomGenerator = new Random();

    public long getHashId(String key, String value){
        return key.hashCode()+value.hashCode();
    }

    public long getHashId(String value){
        return value.hashCode();
    }

    public long getRandomId(){
        return randomGenerator.nextLong();
    }
}
