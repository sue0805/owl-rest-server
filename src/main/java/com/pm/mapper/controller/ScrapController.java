package com.pm.mapper.controller;

import com.pm.mapper.model.member.MemberScrap;
import com.pm.mapper.service.member.MemberScrapService;
import com.pm.mapper.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scrap")
public class ScrapController {

    @Autowired
    private MemberScrapService scrapService;

    @Autowired
    private MemberService memberService;

    // 스크랩 추가
    @PostMapping
    public MemberScrap insert(@RequestBody MemberScrap scrap, @RequestParam("mem_idx") long mem_idx){
        scrap.setMember(memberService.findOne(mem_idx));
        return scrapService.insertOne(scrap);
    }

    // 스크랩 삭제
    @DeleteMapping
    public String delete(@RequestBody MemberScrap scrap, @RequestParam("mem_idx") long mem_idx){
        scrapService.delete(scrap, mem_idx);

        return "삭제 성공";
    }

    // 스크랩 조회
    @GetMapping("/{idx}")
    public MemberScrap findOne(@PathVariable("idx") long idx){ return scrapService.findOne(idx); }


}
