package com.pm.mapper.service.log.es.metadata;

import com.pm.mapper.model.log.es.metadata.ESGoogleData;
import com.pm.mapper.model.log.es.metadata.ESNaverData;
import com.pm.mapper.pojo.Crawler;
import com.pm.mapper.repository.log.es.metadata.ESGoogleDataRepository;
import com.pm.mapper.repository.log.es.metadata.ESNaverDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ESSearchDataService extends Crawler {

    @Autowired
    ESGoogleDataRepository esGoogleDataRepository;

    @Autowired
    ESNaverDataRepository esNaverDataRepository;

    public int insertGoogleData(String keyword){
        List<ESGoogleData> list = collectGoogleData(keyword);
        if(list.size()!=0)
        esGoogleDataRepository.saveAll(list);
        return list.size();
    }

    public int insertGoogleDataList(List<String> keywordList){
        int count = 0;
        for(String keyword : keywordList) {
//            if (esGoogleDataRepository.countByKeyword(keyword) == 0)
                count += insertGoogleData(keyword.replaceAll(" ", ""));
        }
        return count;
    }

    public int insertNaverData(String keyword){
        List<ESNaverData> blogList = collectNaverData(keyword, "blog");
        List<ESNaverData> cafeList = collectNaverData(keyword, "cafearticle");
        if(blogList.size()!=0)
            esNaverDataRepository.saveAll(blogList);
        if(cafeList.size()!=0)
            esNaverDataRepository.saveAll(cafeList);
        return blogList.size()+cafeList.size();
    }

    public int insertNaverDataList(List<String> keywordList){
        int count = 0;
        for(String keyword : keywordList) {
//            if (esNaverDataRepository.countByKeyword(keyword) == 0)
                count += insertNaverData(keyword.replaceAll(" ", ""));
        }
        return count;
    }
}
