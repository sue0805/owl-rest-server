package com.pm.mapper.pojo;

import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class DecodeHelper {

    public String getDecodedString(String keyword){
        try {
            return new String(keyword.getBytes("8859_1"),"utf-8");
        } catch (
                UnsupportedEncodingException e) {
            return keyword;
        }
    }

    public Boolean isKorean(String keyword){
        if(keyword.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*"))
            return true;
        else
            return false;
    }
}
