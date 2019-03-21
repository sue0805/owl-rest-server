package com.pm.mapper.model.unsolvedQuestion;

import com.pm.mapper.repository.unsolvedQuestion.UnsolvedQuestionRepository;
import com.pm.mapper.service.unsolvedQuestion.UnsolvedQuestionService;
import lombok.Data;
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
public class UnsolvedQuestionTest {

    @Autowired
    private UnsolvedQuestion unsolvedQuestion;

    @Autowired
    private UnsolvedQuestionService unsolvedQuestionService;

    @Autowired
    private UnsolvedQuestionRepository unsolvedQuestionRepository;

    // UnsolvedQuestion 객체 확인
    @Test
    public void testUnsolvedQuestion_NotNull(){
        Assert.assertNotNull(unsolvedQuestion);
    }

    // Service > inserQuestion 메서드 Test
//    @Test
//    public void testInsertUnsolvedQuestion() {
//        unsolvedQuestion = new UnsolvedQuestion("작가", "제목", "내용", "Java");
//        unsolvedQuestion.setDate(new Date());
//        unsolvedQuestionRepository.save(unsolvedQuestion);
//        Assert.assertNotNull(unsolvedQuestion);
//    }//통과

    // Service > findQuestion 메서드 Test
    @Test
    public void testFindQuestion(){
         UnsolvedQuestion result = unsolvedQuestionRepository.getOne(1L);
         Assert.assertNotNull(result);
    }//통과

}

