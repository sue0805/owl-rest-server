package com.pm.mapper.repository.log.es.mediacontent;

import com.pm.mapper.model.log.es.mediacontent.ESMediaContent;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ESMediaContentRepository extends ElasticsearchRepository<ESMediaContent, Long> {
}
