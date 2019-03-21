package com.pm.mapper.repository.log.es.metadata;

import com.pm.mapper.model.log.es.metadata.ESYoutubeData;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ESYoutubeDataRepository extends ElasticsearchRepository<ESYoutubeData, Long> {
}
