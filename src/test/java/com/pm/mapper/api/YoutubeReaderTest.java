package com.pm.mapper.api;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
import springfox.documentation.spring.web.json.Json;

import java.util.Iterator;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Slf4j
public class YoutubeReaderTest {
//
//    @Autowired
//    private YoutubeReader youtubeReader;
//
//    //YoutubeReader가 null이 아닌지 확인
//    @Test
//    public void testYoutubeReader_NotNull() {
//        Assert.assertNotNull(youtubeReader);
//    }
//    //초록
//
//    //YouTube크롤링
//    //YouTubeReader의 getResult메서드 테스트
//    @Test
//    public void testYoutubeReaderGetResult() {
//            String connUrl = "https://www.youtube.com/results?search_query=java";
//            Document doc = null;
//            Elements els = null;
//            JSONObject jsonObject = new JSONObject();
//            JSONArray jsonArray = new JSONArray();
//            StringBuffer sb = new StringBuffer();
//
//            try {
//
//                doc = Jsoup.connect(connUrl).maxBodySize(Integer.MAX_VALUE).ignoreContentType(true).get();
//                els = doc.select(".item-section li");
//
//                Iterator<Element> iter = els.iterator();
//
//                while (iter.hasNext()) {
//                    Element el = iter.next();
//
//                    Elements thumbnail = el.select("img");
//                    Elements title = el.select(".yt-lockup-title > a");
//
//                    if (thumbnail.toString().length() > 0) {
//
//                        String src = thumbnail.attr("src");
//
//                        if (src.startsWith("/")) {
//                            src = thumbnail.attr("data-thumb");
//                        }
//                        jsonObject.put("img", src);
//
//                    }
//
//                    if (title.text().length() > 0) {
//                        String link = "https://www.youtube.com" + title.attr("href");
//                        jsonObject.put("title", title.text());
//                        jsonObject.put("link", link);
//
//                        System.out.println(jsonObject);
//                        jsonArray.add(jsonObject);
//                        jsonObject = new JSONObject();
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            log.info(jsonArray.toJSONString());
//    }

    //YoutubeReader의 GetResult 메서드 호출 테스트
//    @Test
//    public void testYoutubeReaderGetResult_Work(){
//        JSONArray result = youtubeReader.getResult("java");
//    }

}
