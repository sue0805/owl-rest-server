package com.pm.mapper.model.review;

import com.pm.mapper.repository.review.ReviewRepository;
import com.pm.mapper.service.review.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ReviewTest {

    @Autowired
    private Review review;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewService reviewService;

    //리뷰 객체가 null이 아닌지 확인한다
    @Test
    public void testReview_NotNull(){
        Assert.assertNotNull(review);
    }

    @Test
    //리뷰의 리스트 3만 가져온다, 3페이지
    public void testReviewList3(){
        PageRequest pageRequest = PageRequest.of(3,3, new Sort(Sort.Direction.DESC,"date"));
        Page<Review> result = reviewRepository.findAllByKeywordIgnoreCase("Java", pageRequest);
        log.info(result.toString());
    }
    //통과

    //리뷰 리스트 3개만 가져오는 컨트롤러 테스트
    @Test
    public void testReviewList3Controller(){
        String.valueOf(reviewService.reviewList3("java", 1));
    }
    //통과

    @Test
    //리뷰 리스트 100개 가져온다
    public void testReviewList100(){
        PageRequest pageRequest = PageRequest.of(0,100, new Sort(Sort.Direction.DESC,"date"));
        Page<Review> result = reviewRepository.findAllByKeywordIgnoreCase("Java", pageRequest);
        log.info(result.toString());
    }
    //통과

    //모두 가져오는 메서드 테스트
    @Test
    public void testReviewServiceListAll(){
        //대소문자 구분없이 내림차순으로 키워드에 맞게 모두 찾는다.
        List<Review> result= reviewRepository.findAllByKeywordIgnoreCaseOrderByIdDesc("java");
        Assert.assertNotNull(result);
    }
    //통과


    //리뷰의 데이터를 넣는 테스트
    @Test
    public void testReviewInsert(){
        review = reviewRepository.save(review);
        log.info(review.getKeyword());
        reviewRepository.delete(review);
    }
    //통과

    //ReviewService insertOne메서드 테스트
    @Test
    public void testReviewControllerInsertOne_Work(){
        Review result =  reviewService.insertOne(review);
        Assert.assertNotNull(result);
        reviewRepository.delete(result);
    }
    //통과


}
