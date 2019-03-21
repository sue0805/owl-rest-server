package com.pm.mapper.service.sortedQuestion;

import com.pm.mapper.model.sortedQuestion.SortedQuestion;
import com.pm.mapper.repository.sortedQuestion.SortedQuestionRepository;
import com.pm.mapper.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SortedQuestionService {

    @Autowired
    SortedQuestionRepository sortedQuestionRepository;

    public List<SortedQuestion> findAllByKeywordWithPaging(String keyword, int page) {
        PageRequest pageRequest = PageRequest.of(page, 20, new Sort(Sort.Direction.DESC, "date"));
        Page<SortedQuestion> result = sortedQuestionRepository.findAllByTagsContainsIgnoreCase(keyword, pageRequest);
        return result.getContent();
    }

    public SortedQuestion findOne(long idx){
        return sortedQuestionRepository.getOne(idx);
    }

    public List<SortedQuestion> findAllOrderByViews(){
        return sortedQuestionRepository.findAllDateAfter7Day();
    }

    public SortedQuestion save(SortedQuestion question) {
        return sortedQuestionRepository.save(question);
    }

    public void deleteByQuestion(SortedQuestion question){
        sortedQuestionRepository.delete(question);
    }
}
