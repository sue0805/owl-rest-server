package com.pm.mapper.repository.unsolvedQuestion;

import com.pm.mapper.model.unsolvedQuestion.UnsolvedQuestion;
import com.pm.mapper.model.unsolvedQuestion.UnsolvedQuestionReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnsolvedQuestionReplyRepository extends JpaRepository<UnsolvedQuestionReply, Long> {

    //메인 게시물 번호에 의해서 댓글을 모두 가져온다
    List<UnsolvedQuestionReply> findAllByUnsolvedQuestionOrderByUpdateDateDesc(UnsolvedQuestion id);

    //기존에 있는 댓글을 모두 가져온다
    List<UnsolvedQuestionReply> findAll();

}

