package com.pm.mapper.controller;

import com.pm.mapper.api.FnNewsRss;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HomeController {

    @Value("${spring.api.naver.ClientID}")
    private String naverClientID;
    @Value("${spring.api.naver.ClientSecret}")
    private String naverClientSecret;
    @Value("${spring.api.kakao.ClientID}")
    private String kakaoClientID;
    @Value("${spring.api.kakao.ClientSecret}")
    private String kakaoClientSecret;
    @Value("${spring.api.github.ClientID}")
    private String githubClientID;
    @Value("${spring.api.github.ClientSecret}")
    private String githubClientSecret;
    @Value("${spring.api.unsplash.ClientID}")
    private String unsplashClientID;
    @Value("${spring.api.googleSearch.ApiKey}")
    private String googleSearchApiKey;
    @Value("${spring.api.googleCustomSearch.ApiKey}")
    private String googleCustomSearchApiKey;


    @Autowired
    private FnNewsRss fnNewsRss;

    @GetMapping("/news")
    public JSONArray news(){
        return fnNewsRss.getITNews();
    }

    @GetMapping("/apis")
    public Map<String, String[]> getApis(){

        Map<String, String[]> map = new HashMap<>();
        map.put("naver", new String[] {naverClientID, naverClientSecret});
        map.put("kakao", new String[] {kakaoClientID, kakaoClientSecret});
        map.put("github", new String[] {githubClientID, githubClientSecret});
        map.put("googleSearch", new String[] {googleSearchApiKey});
        map.put("googleCustomSearch", new String[] {googleCustomSearchApiKey});
        map.put("unsplash", new String[] {unsplashClientID});

        return map;
    }
}
