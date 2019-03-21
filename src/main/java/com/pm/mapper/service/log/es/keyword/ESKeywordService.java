package com.pm.mapper.service.log.es.keyword;

import com.pm.mapper.model.log.es.keyword.ESEtcKeyword;
import com.pm.mapper.model.log.es.keyword.ESKeyword;
import com.pm.mapper.model.log.es.keyword.ESKoreanKeyword;
import com.pm.mapper.pojo.DecodeHelper;
import com.pm.mapper.repository.log.es.keyword.ESEtcKeywordRepository;
import com.pm.mapper.repository.log.es.keyword.ESKeywordRepository;
import com.pm.mapper.repository.log.es.keyword.ESKoreanKeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ESKeywordService extends DecodeHelper {

    @Autowired
    ESEtcKeywordRepository esEtcKeywordRepositoryEtc;

    @Autowired
    ESKoreanKeywordRepository esKoreanKeywordRepository;

    @Autowired
    ESKeywordRepository esKeywordRepository;

    public String insertKeywordLog(String keyword, List<String> keywords, String user){
        user = getDecodedString(user);
        for(String i : keywords){ // 분석된 키워드 입력 반복문
            i = getDecodedString(i);
            if(isKorean(i))
                esKoreanKeywordRepository.save(new ESKoreanKeyword(i, user));
            else
                esEtcKeywordRepositoryEtc.save(new ESEtcKeyword(i, user));
        }
        esKeywordRepository.save(new ESKeyword(getDecodedString(keyword), user)); // 분석 전 전체 키워드 입력
        System.out.printf("keyword: %s, user: %s", getDecodedString(keyword), user);
        return "Insert Complete";
    }
}
