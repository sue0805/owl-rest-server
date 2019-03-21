package com.pm.mapper.controller;

import com.pm.mapper.api.NaverAPI;
import com.pm.mapper.api.YoutubeReader;
import com.pm.mapper.model.review.Review;
import com.pm.mapper.service.member.MemberService;
import com.pm.mapper.service.review.ReviewService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private NaverAPI naverAPI;

/* ================== 리뷰 검색 + 추가 ================ */

    @GetMapping("/review/{keyword}/{page}")
    public Page<Review> reviewList3(@PathVariable("keyword") String keyword, @PathVariable("page") int page){
        return reviewService.reviewList3(keyword, page);
    }

    @PostMapping("/review")
    public Review insert(@RequestBody Review review, @RequestParam("mem_idx") long mem_idx){
        review.setMember(memberService.findOne(mem_idx));
        return reviewService.insertOne(review);
    }

/* =================== 네이버 검색 ===================== */

    @GetMapping("/naver/{category}/{keyword}/{page}/{sort}")
    public JSONObject naverSearch(@PathVariable("keyword") String keyword, @PathVariable("page") int page,
                                  @PathVariable("category") String category, @PathVariable("sort") String sort){

        return naverAPI.search(category, keyword, page, sort);
    }

/* =================== 유튜브 검색 ===================== */

    @GetMapping("/youtube/{keyword}")
    public JSONArray youtube(@PathVariable("keyword") String keyword){return new YoutubeReader().getResult(keyword);}

}
