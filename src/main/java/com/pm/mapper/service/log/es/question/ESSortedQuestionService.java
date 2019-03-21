package com.pm.mapper.service.log.es.question;

import com.pm.mapper.model.log.es.question.ESSortedQuestion;
import com.pm.mapper.pojo.DecodeHelper;
import com.pm.mapper.repository.log.es.question.ESSortedQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ESSortedQuestionService extends DecodeHelper {

    @Autowired
    ESSortedQuestionRepository esSortedQuestionRepository;

    public String insertSortedQuestionLog(String questionId, String user){
        esSortedQuestionRepository.save(
                new ESSortedQuestion(getDecodedString(questionId), getDecodedString(user))
        );
        return "Logging Complete";
    }

}
