package com.pm.mapper.api;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Component
public class NaverAPI {



    public JSONObject search(String category, String keyword, int page, String sort){

        String clientId = "kZPq6rWjhs16cUmHFt9m";
        String clientSecret = "KGYQL9mraR";
        JSONObject jsonObject = null;
        StringBuffer response = new StringBuffer();
        try {
            String text = URLEncoder.encode(keyword, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/"+ category +"?display=13&query="+ text + "&start=" + page + "&sort=" + sort; // json 결과
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
            }
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            JSONParser parser = new JSONParser();
            jsonObject = (JSONObject) parser.parse(response.toString());

        } catch (Exception e) {
            System.out.println(e);
        }

        return jsonObject;
    }
}
