package com.pm.mapper.api;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;


//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Slf4j
public class GoogleSearchTest {

//    @Autowired
//    private GoogleSearch googleSearch;
//
//    @Test
//    public void testGoogleSearch_NotNull(){
//        Assert.assertNotNull(googleSearch);
//    }
//
//    @Test
//    public void testGetResult(){
//        Document doc = null;
//        JSONObject jobj = new JSONObject();
//        JSONArray jarr = new JSONArray();
//        Elements els = null;
//        String str = "";
//
//        for(int i = 0; i < 3; i++) {
//            try {
//
//                String connUrl = "https://www.google.com/search?q=java&start=" + (i*10);
//
//                doc = Jsoup.connect(connUrl).maxBodySize(Integer.MAX_VALUE).ignoreContentType(true).get();
//                JSONParser parser = new JSONParser();
//
//                els = doc.select(".srg");
//
//                els = els.select(".r > a, .s .st").not(".fl");
//
//                Iterator<Element> iter = els.iterator();
//
//                String link = "";
//                String title = "";
//                String content = "";
//
//                while (iter.hasNext()) {
//
//                    Element el = iter.next();
//
//                    if (!el.select("a").isEmpty()) {
//                        link = el.select("a").attr("href");
//                        title = el.select("h3").text();
////                        System.out.println(link);
//                        jobj.put("link", link);
//                        jobj.put("title", title);
////                        System.out.println(link);
////                        System.out.println(title);
//                    } else {
//                        content = el.text();
////                        System.out.println(content);
//                        jobj.put("content", content);
//                        jarr.add(jobj);
//                        jobj = new JSONObject();
//                    }
////                    System.out.println();
//                }
////                System.out.println(jarr);
//            } catch (Exception e) {
//
//                e.printStackTrace();
//            }
//        }
//        Assert.assertNotNull(jarr.toJSONString());
//    }
//
//    //구글 클래스의 getResult메서드 확인
//    @Test
//    public void testGoogleSearchGetResult(){
//        JSONArray google = googleSearch.getResult("java");
//        log.info(google.toJSONString());
//        //초록불
//    }
}
