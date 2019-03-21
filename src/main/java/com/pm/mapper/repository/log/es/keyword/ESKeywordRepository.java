package com.pm.mapper.repository.log.es.keyword;

import com.pm.mapper.model.log.es.keyword.ESKeyword;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

public interface ESKeywordRepository extends ElasticsearchRepository<ESKeyword, Long> {
}
