package com.pm.mapper.service.unsolvedQuestion;

import com.pm.mapper.model.unsolvedQuestion.UnsolvedQuestion;
import com.pm.mapper.model.unsolvedQuestion.UnsolvedQuestionReply;
import com.pm.mapper.repository.unsolvedQuestion.UnsolvedQuestionReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnsolvedQuestionReplyService {

    @Autowired
    private UnsolvedQuestionReplyRepository unsolvedQuestionReplyRepository;

//    답변댓글 insert 다른 방향
    public UnsolvedQuestionReply insertOne(UnsolvedQuestionReply reply){
        return unsolvedQuestionReplyRepository.save(reply);
    }

    //배열로 답변 댓글을 모두 찾는다. 수정날짜에 내림차순해서

    //제일 최근꺼 하나만 select 하기
    public List<UnsolvedQuestionReply> getList(UnsolvedQuestion unsolvedQuestion){
        //해결되지 않은 질문객체로 댓글을 모두 찾는다.
        return unsolvedQuestionReplyRepository.findAllByUnsolvedQuestionOrderByUpdateDateDesc(unsolvedQuestion);
    }

    //답변 댓글을 모두 찾는다.
    public List<UnsolvedQuestionReply> getList(){
        return unsolvedQuestionReplyRepository.findAll();
    }

    public void deleteByReply(UnsolvedQuestionReply reply){
        unsolvedQuestionReplyRepository.delete(reply);
    }

    public UnsolvedQuestionReply findOne(long idx){return unsolvedQuestionReplyRepository.getOne(idx);}
}
