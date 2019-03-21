package com.pm.mapper.service.log.es.metadata;

import com.pm.mapper.model.log.es.metadata.ESYoutubeData;
import com.pm.mapper.pojo.DecodeHelper;
import com.pm.mapper.repository.log.es.metadata.ESYoutubeDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ESYoutubeDataService extends DecodeHelper {

    @Autowired
    ESYoutubeDataRepository esYoutubeDataRepository;

    public String insertYoutubeData(String keyword, Map<String, List> map){
        List<ESYoutubeData> dataModelList = new ArrayList();
        for(Map.Entry<String, List> entry: map.entrySet() ){
            List<String> values = entry.getValue();
            dataModelList.add(new ESYoutubeData(keyword, values.get(0), values.get(1), values.get(2)));
        }
        esYoutubeDataRepository.saveAll(dataModelList);
        return "Insert Complete";
    }
}
