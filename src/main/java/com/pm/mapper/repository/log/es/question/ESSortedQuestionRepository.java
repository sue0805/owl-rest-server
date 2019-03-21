package com.pm.mapper.repository.log.es.question;

import com.pm.mapper.model.log.es.question.ESSortedQuestion;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ESSortedQuestionRepository extends ElasticsearchRepository<ESSortedQuestion, Long> {
}
