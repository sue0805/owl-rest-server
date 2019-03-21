package com.pm.mapper.service.unsolvedQuestion;

import com.pm.mapper.model.unsolvedQuestion.UnsolvedQuestion;
import com.pm.mapper.repository.unsolvedQuestion.UnsolvedQuestionRepository;
import com.pm.mapper.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UnsolvedQuestionService {

    @Autowired
    UnsolvedQuestionRepository unsolvedQuestionRepository;

    private UnsolvedQuestion unsolvedQuestion;

    //해결되지 않은 질문 컴포넌트에 내용을 넣을 메서드를 만든다.
    public UnsolvedQuestion insertQuestion(String writer, String title, String content, String tags){
        unsolvedQuestion = new UnsolvedQuestion(writer, title, content, tags);
        unsolvedQuestion.setDate(new Date());
        //저장소에 내용을 저장한다.
        unsolvedQuestionRepository.save(unsolvedQuestion);

        return unsolvedQuestion;
    }

    //unsolvedQuestion의 id값을 찾는 메서드
    public UnsolvedQuestion findQuestion(long id){
        return unsolvedQuestionRepository.getOne(id);
    }

    public List<UnsolvedQuestion> findAll(){
        return unsolvedQuestionRepository.findAll();
    }

    public void deleteByQuestion(UnsolvedQuestion question){
        unsolvedQuestionRepository.delete(question);
    }

    public UnsolvedQuestion save(UnsolvedQuestion question){
        return unsolvedQuestionRepository.save(question);}
}
