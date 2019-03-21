package com.pm.mapper.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class YoutubeReader {

    public JSONArray getResult(String keyword){

        String connUrl = "https://www.youtube.com/results?search_query=" + keyword;
        Document doc = null;
        Elements els = null;
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        StringBuffer sb = new StringBuffer();

        try{

            doc = Jsoup.connect(connUrl).maxBodySize(Integer.MAX_VALUE).ignoreContentType(true).get();
            els = doc.select(".item-section li");

            Iterator<Element> iter = els.iterator();

            while(iter.hasNext()){
                Element el = iter.next();

                Elements thumbnail = el.select("img");
                Elements title = el.select(".yt-lockup-title > a");

                if(thumbnail.toString().length() > 0) {

                    String src = thumbnail.attr("src");

                    if(src.startsWith("/")){
                        src = thumbnail.attr("data-thumb");
                    }
                    jsonObject.put("img", src);

                }

                if(title.text().length() > 0) {
                    String link = "https://www.youtube.com" + title.attr("href");
                    jsonObject.put("title", title.text());
                    jsonObject.put("link", link);

                    jsonArray.add(jsonObject);
                    jsonObject = new JSONObject();
                }
            }


        } catch (Exception e){
            e.printStackTrace();
        }

        return jsonArray;
    }
}
