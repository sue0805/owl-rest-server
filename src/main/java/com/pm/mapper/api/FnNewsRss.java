package com.pm.mapper.api;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//의존성 주입하는 거, 빈으로 등록하기
@Component
public class FnNewsRss {

    public org.json.simple.JSONArray getITNews(){

        String url_str = "http://www.fnnews.com/rss/new/fn_manyview_it.xml";
        JSONObject object = null;
        org.json.simple.JSONObject jsonObject = null;
        org.json.simple.JSONArray jsonArray = null;
        JSONArray jarr = null;

        try {

            URL url = new URL(url_str);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

            StringBuilder response = new StringBuilder();
            String inputLine;

            while((inputLine = br.readLine()) != null){
                response.append(inputLine);
            }

            object = XML.toJSONObject(response.toString());
            object = object.getJSONObject("rss").getJSONObject("channel");
            jarr = object.getJSONArray("item");

            JSONParser parser = new JSONParser();
            jsonArray = (org.json.simple.JSONArray)parser.parse(jarr.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonArray;
    }
}
