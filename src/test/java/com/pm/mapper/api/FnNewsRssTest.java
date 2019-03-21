package com.pm.mapper.api;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class FnNewsRssTest {

    @Autowired
    private FnNewsRss fnNewsRss;

    @Test
    public void testFnNewsRss_NotNull(){
        Assert.assertNotNull(fnNewsRss);
    }
}
