package com.pm.mapper.controller;

import com.pm.mapper.service.log.es.keyword.ESKeywordService;
import com.pm.mapper.service.log.es.mediacontent.ESMediaContentService;
import com.pm.mapper.service.log.es.metadata.ESSearchDataService;
import com.pm.mapper.service.log.es.metadata.ESYoutubeDataService;
import com.pm.mapper.service.log.es.question.ESSortedQuestionService;
import com.pm.mapper.service.log.es.searchresult.ESSearchResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private ESKeywordService esKeywordService;

    @Autowired
    private ESMediaContentService esMediaContentService;

    @Autowired
    private ESSortedQuestionService esSortedQuestionService;

    @Autowired
    private ESSearchResultService esSearchResultService;

    @Autowired
    private ESYoutubeDataService esYoutubeDataService;

    @Autowired
    private ESSearchDataService esSearchDataService;

    // 키워드 로그 수집
    @RequestMapping(value = "/keyword/insert", method = RequestMethod.POST)
    public String insertLog(@RequestParam("keyword") String keyword,
                            @RequestParam("keywords") List<String> keywords,
                            @RequestParam("user") String user
    ){
        return esKeywordService.insertKeywordLog(keyword, keywords, user);
    }

    // Media Content 로그 수집
    @RequestMapping(value = "/mediacontent/insert", method = RequestMethod.POST)
    public String insertMediaContentLog(
            @RequestParam("link") String link,
            @RequestParam("title") String title,
            @RequestParam("div") String div,
            @RequestParam("user") String user
    ){
        return esMediaContentService.insertMediaContentLog(title, link, div, user);
    }

    // 해결된 질문 로그 수집
    @RequestMapping(value = "/question/sorted/insert", method = RequestMethod.POST)
    public String insertSortedQuestionLog(@RequestParam("questionId") String questionId,
                                          @RequestParam("user") String user){
        return esSortedQuestionService.insertSortedQuestionLog(questionId, user);
    }

    // 검색 결과 로그 수집
    @RequestMapping(value = "/searchresult/insert", method = RequestMethod.POST)
    public String insertSearchResultLog(
            @RequestParam("title") String title,
            @RequestParam("link") String link,
            @RequestParam("div") String div,
            @RequestParam("user") String user
    ){
        return esSearchResultService.insertSearchResultLog(title, link, div, user);
    }

    // 유튜브 로그 수집
    @RequestMapping(value="/metadata/youtube/insert", method=RequestMethod.POST)
    public String insertYoutubeData(@RequestParam String keyword, @RequestBody Map<String, List> map){
        return esYoutubeDataService.insertYoutubeData(keyword, map);
    }

    @RequestMapping(value="/log/searchdata/insert", method= RequestMethod.POST)
    public String collectData(@RequestBody List<String> keywordList){
        int count = 0;
        count += esSearchDataService.insertGoogleDataList(keywordList);
        count += esSearchDataService.insertNaverDataList(keywordList);
        return count + " Data Insert Complete!";
    }
}
