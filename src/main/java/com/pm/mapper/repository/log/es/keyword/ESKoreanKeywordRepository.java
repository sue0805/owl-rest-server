package com.pm.mapper.repository.log.es.keyword;

import com.pm.mapper.model.log.es.keyword.ESKoreanKeyword;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

public interface ESKoreanKeywordRepository extends ElasticsearchRepository<ESKoreanKeyword, Long> {
}