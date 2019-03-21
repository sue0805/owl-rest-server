package com.pm.mapper.service.review;

import com.pm.mapper.model.review.Review;
import com.pm.mapper.repository.review.ReviewRepository;
import com.pm.mapper.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    //리뷰의 리스트 3만 가져온다.
    public Page<Review> reviewList3(String keyword, int page){
        // 날짜를 기준으로 내림차순하여 3개의 목록만 가져온다. //몇 페이지인지에 따라서 3개씩
        PageRequest pageRequest = PageRequest.of(page, 3, new Sort(Sort.Direction.DESC, "date"));
        // 페이지 처리를 한다. 대소문자구별없이 키워드 기준으로 모두를 찾는다. 사이즈는 3개.
        Page<Review> result = repository.findAllByKeywordIgnoreCase(keyword, pageRequest);
        //페이지 처리한 result의 내용을 받아온다.
        return result;
    }

    public Review insertOne(Review review){
        //저장소에 리뷰테이블을 저장한다.
        return repository.save(review);

    }
}
