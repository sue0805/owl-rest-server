package com.pm.mapper.repository.log.es.keyword;

import com.pm.mapper.model.log.es.keyword.ESEtcKeyword;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

public interface ESEtcKeywordRepository extends ElasticsearchRepository<ESEtcKeyword, Long> {
}
