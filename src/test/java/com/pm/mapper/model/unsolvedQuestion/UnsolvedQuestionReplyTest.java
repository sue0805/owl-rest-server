package com.pm.mapper.model.unsolvedQuestion;

import com.pm.mapper.repository.unsolvedQuestion.UnsolvedQuestionReplyRepository;
import com.pm.mapper.repository.unsolvedQuestion.UnsolvedQuestionRepository;
import com.pm.mapper.service.member.MemberService;
import com.pm.mapper.service.unsolvedQuestion.UnsolvedQuestionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UnsolvedQuestionReplyTest {

    @Autowired

    private UnsolvedQuestionReply unsolvedQuestionReply;

    @Autowired
    private UnsolvedQuestionReplyRepository unsolvedQuestionReplyRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private UnsolvedQuestionService unsolvedQuestionService;

    @Autowired
    private UnsolvedQuestionRepository unsolvedQuestionRepository;

    //
    @Test
    public void testUnsolvedQuestionReply_NotNull(){
        Assert.assertNotNull(unsolvedQuestionReply);
    }

    //댓글 테이블에 값 넣기
//    @Test
//    public void testUnsolvedQuestionReply_Insert(){
//        UnsolvedQuestionReply unsolvedQuestionReply = new UnsolvedQuestionReply();
//        unsolvedQuestionReply.setReply_content("포트를 바꿔보세요");
//        unsolvedQuestionReply.setReply_writer("용재");
//        unsolvedQuestionReply.setReplyDate(new Date());
//        unsolvedQuestionReply.setMember(memberService.getMemberByEmail("test@test.com"));
//        unsolvedQuestionReply.setUnsolvedQuestion(unsolvedQuestionRepository.getOne(2472L));
//        unsolvedQuestionReplyRepository.save(unsolvedQuestionReply);
//        Assert.assertNotNull(unsolvedQuestionReply);
//        unsolvedQuestionReplyRepository.delete(unsolvedQuestionReply);
//    }

}