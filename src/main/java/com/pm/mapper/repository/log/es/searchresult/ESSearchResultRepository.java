package com.pm.mapper.repository.log.es.searchresult;

import com.pm.mapper.model.log.es.searchresult.ESSearchResult;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ESSearchResultRepository extends ElasticsearchRepository<ESSearchResult, Long> {
}
