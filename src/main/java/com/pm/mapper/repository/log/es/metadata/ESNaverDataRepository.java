package com.pm.mapper.repository.log.es.metadata;

import com.pm.mapper.model.log.es.metadata.ESNaverData;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ESNaverDataRepository extends ElasticsearchRepository<ESNaverData, Long> {
    long countByKeyword(String keyword);
}
