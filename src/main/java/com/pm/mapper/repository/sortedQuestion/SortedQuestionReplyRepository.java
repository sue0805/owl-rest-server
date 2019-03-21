package com.pm.mapper.repository.sortedQuestion;

import com.pm.mapper.model.sortedQuestion.SortedQuestion;
import com.pm.mapper.model.sortedQuestion.SortedQuestionReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SortedQuestionReplyRepository extends JpaRepository<SortedQuestionReply, Long> {

    List<SortedQuestionReply> findAllBySortedQuestion(SortedQuestion sortedQuestion);
}
