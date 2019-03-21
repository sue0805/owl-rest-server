package com.pm.mapper.api;

import javassist.runtime.Desc;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

//@SpringBootTest
//@RunWith(SpringRunner.class)
//@Slf4j
public class NaverAPITest {
//
//    @Autowired
//    private NaverAPI naverAPI;
//
//    @Test
//    public void testNaverAPI_NotNull() {
//        Assert.assertNotNull(naverAPI);
//    }
//
//    @Test
//    public void testNaverSearch() {
//        String keyword = "Java";
//        String clientId = "kZPq6rWjhs16cUmHFt9m";
//        String clientSecret = "KGYQL9mraR";
//        JSONObject jsonObject = null;
//        StringBuffer response = new StringBuffer();
//        try {
//            String text = URLEncoder.encode(keyword, "UTF-8");
//            String apiURL = "https://openapi.naver.com/v1/search/blog?display=20&query=" + text; // json 결과
//            URL url = new URL(apiURL);
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//            con.setRequestProperty("X-Naver-Client-Id", clientId);
//            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
//            int responseCode = con.getResponseCode();
//            BufferedReader br;
//            if (responseCode == 200) { // 정상 호출
//                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
//            } else {  // 에러 발생
//                br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
//            }
//            String inputLine;
//            while ((inputLine = br.readLine()) != null) {
//                response.append(inputLine);
//            }
//            br.close();
//            JSONParser parser = new JSONParser();
//            jsonObject = (JSONObject) parser.parse(response.toString());
//            System.out.println(jsonObject);
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//        Assert.assertNotNull(jsonObject.toJSONString());
//    }
//
//    //naver npi 적상 작동 되는 지 확인
//    @Test
//    public void testNaverSearchApi(){
//        naverAPI.search("blog", "java", 1, "sim");
//        Assert.assertNotNull(naverAPI.toString());
//        //확인
//    }
}
