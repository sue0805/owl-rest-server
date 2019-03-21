package com.pm.mapper.api;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static jdk.nashorn.internal.runtime.regexp.joni.Syntax.Java;

//@SpringBootTest
//@RunWith(SpringRunner.class)
//@Slf4j
public class WikipediaReaderTest {

//    @Autowired
//    private WikipediaReader wikipediaReader;

//    @Test
//    public void testWikipediaReader_NotNull() {
//        Assert.assertNotNull(wikipediaReader);
//    }
//
//    @Test
//    public void testWikiRead() {
//        Document doc = null;
//        JSONObject jobj = null;
//        Elements els = null;
//        String str = "";
//        String connUrl = "";
//
//        try {
//
//            connUrl = "https://ko.wikipedia.org/w/index.php?search=" + Java;
//            doc = Jsoup.connect(connUrl).maxBodySize(Integer.MAX_VALUE).ignoreContentType(true).get();
//
//            JSONParser parser = new JSONParser();
//
//            els = doc.select("#mw-content-text > div > :lt(4)");
//            els = els.not(".toc, table, form");
//
//            str = els.toString();
//            str = str.replace("\"/wiki", "\"https://ko.wikipedia.org/wiki");
//            str = str.replace("<a", "<a target='_blank'");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Assert.assertNotNull(str);
//    }

    //위키피디아 readWiki 메서드 호출 테스트
//    @Test
//    public void testReadWiki(){
//        String wiki = wikipediaReader.readWiki("java");
//        log.info(wiki);
//        //초록
//    }


}
