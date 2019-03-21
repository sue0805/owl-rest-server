package com.pm.mapper.service.sortedQuestion;

import com.pm.mapper.model.sortedQuestion.SortedQuestion;
import com.pm.mapper.model.sortedQuestion.SortedQuestionReply;
import com.pm.mapper.repository.sortedQuestion.SortedQuestionReplyRepository;
import com.pm.mapper.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SortedQuestionReplyService {

    @Autowired
    private SortedQuestionReplyRepository repository;

    @Autowired
    private MemberService memberService;

    public SortedQuestionReply insertOne(SortedQuestionReply reply) {
        reply.setReplyDate(new Date());
        return repository.save(reply);
    }

    public List<SortedQuestionReply> getList(){
        return repository.findAll();
    }

    public List<SortedQuestionReply> getList(SortedQuestion sortedQuestion){
        return repository.findAllBySortedQuestion(sortedQuestion);
    }

    public void deleteByReply(SortedQuestionReply reply){
        repository.delete(reply);
    }
}
