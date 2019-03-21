package com.pm.mapper.service.log.es.searchresult;

import com.pm.mapper.model.log.es.searchresult.ESSearchResult;
import com.pm.mapper.pojo.DecodeHelper;
import com.pm.mapper.repository.log.es.searchresult.ESSearchResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ESSearchResultService extends DecodeHelper {

    @Autowired
    ESSearchResultRepository esSearchResultRepository;

    public String insertSearchResultLog(String title, String link, String div, String user){
        esSearchResultRepository.save(new ESSearchResult(getDecodedString(title), getDecodedString(link), getDecodedString(div), getDecodedString(user)));
        return "Logging Complete";
    }
}
