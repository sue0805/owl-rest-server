package com.pm.mapper.repository.review;

import com.pm.mapper.model.review.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    //페이징 처리를 한다. 대소문자 무시하고 키워드기준으로 모든걸 찾는다.
    Page<Review> findAllByKeywordIgnoreCase(String keyword, Pageable pageable);
    //리스트를 불러온다. 대소문자 무시하고 내림차순으로 키워드를 기준으로 모든것을 찾는다.
    List<Review> findAllByKeywordIgnoreCaseOrderByIdDesc(String keyword);
}
