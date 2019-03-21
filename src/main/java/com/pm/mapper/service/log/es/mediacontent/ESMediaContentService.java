package com.pm.mapper.service.log.es.mediacontent;

import com.pm.mapper.model.log.es.mediacontent.ESMediaContent;
import com.pm.mapper.pojo.DecodeHelper;
import com.pm.mapper.repository.log.es.mediacontent.ESMediaContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ESMediaContentService extends DecodeHelper {

    @Autowired
    ESMediaContentRepository esMediaContentRepository;

    public String insertMediaContentLog(String title, String link, String div, String user){
        esMediaContentRepository.save(new ESMediaContent(getDecodedString(title), getDecodedString(link), getDecodedString(div), getDecodedString(user)));
        return "Media Content Logging Complete";
    }

}
