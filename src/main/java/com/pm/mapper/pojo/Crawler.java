package com.pm.mapper.pojo;

import com.pm.mapper.api.NaverAPI;
import com.pm.mapper.model.log.es.metadata.ESGoogleData;
import com.pm.mapper.model.log.es.metadata.ESNaverData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Crawler {

    @Value("${spring.api.googleSearch.ApiKey}")
    String apiKey;

    @Value("${spring.api.googleSearch.SearchEngineId}")
    String searchEngineId;

    @Autowired
    NaverAPI naverAPI;

    public List<ESGoogleData> collectGoogleData(String keyword)  {
        List<ESGoogleData> searchDataList = new ArrayList();

        JSONParser jsonParser = new JSONParser();

        String url = "https://www.googleapis.com/customsearch/v1?key="+apiKey+"&cx="+searchEngineId+"&q="+keyword;
        Document doc = null;
        try {
            doc = Jsoup.connect(url).ignoreContentType(true).get();
        } catch (IOException e) {
            System.err.println(e);
        }
//        String body = doc.selectFirst("body").text();
        System.out.println(doc.text());

        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(doc.text());
            Object items_obj = jsonObject.get("items");
            JSONArray dataList = (JSONArray) items_obj;

            for (int i = 0; i < dataList.size(); i++) {
                JSONObject data = (JSONObject) dataList.get(i);
                String link = data.get("link").toString();
                String title = data.get("htmlTitle").toString();
                String snippet = data.get("htmlSnippet").toString();
                String img;
                try {
                    JSONObject pagemap = (JSONObject) data.get("pagemap");
                    JSONArray cse_thumbnail = (JSONArray) pagemap.get("cse_thumbnail");
                    JSONObject img_info = (JSONObject) cse_thumbnail.get(0);
                    img = img_info.get("src").toString();
                } catch (NullPointerException e){
                    img = null;
                }
                ESGoogleData oneOfData = new ESGoogleData(keyword,link,title,snippet,img);
                searchDataList.add(oneOfData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchDataList;
    }

    public List<ESNaverData> collectNaverData(String keyword, String category){
        List<ESNaverData> searchDataList = new ArrayList();

        try {
            JSONObject naverData = naverAPI.search(category,"keyword",1,"sim");
            Object items_obj = naverData.get("items");
            JSONArray dataList = (JSONArray) items_obj;

            for (int i = 0; i < dataList.size(); i++) {
                JSONObject data = (JSONObject) dataList.get(i);
                String link = data.get("link").toString();
                String title = data.get("title").toString();
                String snippet = data.get("description").toString();
                ESNaverData oneOfData = new ESNaverData(keyword,link,title,snippet);
                searchDataList.add(oneOfData);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        return searchDataList;
    }
}
