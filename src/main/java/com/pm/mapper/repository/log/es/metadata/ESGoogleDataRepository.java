package com.pm.mapper.repository.log.es.metadata;

import com.pm.mapper.model.log.es.metadata.ESGoogleData;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ESGoogleDataRepository extends ElasticsearchRepository<ESGoogleData, Long> {
    long countByKeyword(String keyword);
}
