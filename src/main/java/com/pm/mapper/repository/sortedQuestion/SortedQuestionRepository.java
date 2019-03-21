package com.pm.mapper.repository.sortedQuestion;

import com.pm.mapper.model.sortedQuestion.SortedQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SortedQuestionRepository extends JpaRepository<SortedQuestion, Long> {
    //현재 날짜에서 7일 이내에의 게시물에서 views의 내림차순으로 정렬하여 조회한다.
    @Query(value = "select * from mapper.sorted_question s where date > (NOW() - INTERVAL 7 day ) order by views desc limit 10 ", nativeQuery = true)
    List<SortedQuestion> findAllDateAfter7Day();

    Page<SortedQuestion> findAllByTagsContainsIgnoreCase(String keyword, Pageable pageable);

}
