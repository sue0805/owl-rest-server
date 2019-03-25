package com.pm.mapper.controller;

import com.pm.mapper.model.member.Member;
import com.pm.mapper.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // 홈페이지 회원 가입 (API X)
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    //RequestParam을 사용해서 매개변수 값을 넘겨준다.
    public String signUp(@RequestParam(value = "email", required = true) String email,
                         @RequestParam(value = "nickname", required = true) String nickname,
                         @RequestParam(value = "password", required = true) String password){
        //nickname , password 인코딩
        try {
            nickname = new String(nickname.getBytes("8859_1"),"utf-8");
            password = new String(password.getBytes("8859_1"),"utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return memberService.signUp(email,nickname,password);
    }

    //로그인
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public Map signIn(
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "password", required = true) String password){
        return memberService.signIn(email,password);
    }

    // 회원 정보 받기
    @GetMapping("/{mem_idx}")
    public Member memberInfo(@PathVariable("mem_idx") long idx) { return memberService.findOne(idx); }

    //회원 정보 이메일로 받기
    @GetMapping
    public Member memberInfo(@RequestParam("email") String email){ return memberService.getMemberByEmail(email);}

    // 회원 정보 수정
    @PostMapping("/edit")
    public Member editMember(@RequestBody Map<String, String> map){
        Member member = memberService.findOne(Long.parseLong(map.get("mem_idx")));
        if(map.containsKey("nickname")) member.setNickname(map.get("nickname"));
        if(map.containsKey("password")){
            if(member.getPassword().equals(map.get("currpwd"))) member.setPassword(map.get("password"));
            else return null;
        }
        return memberService.update(member);
    }

    // 회원 탈퇴
    @DeleteMapping("/delete")
    public int delMember(@RequestBody Map<String, String> map){
        Member member = memberService.findOne(Long.parseLong(map.get("mem_idx")));
        if(!member.getPassword().equals(map.get("password"))) return 0;
        memberService.delete(member);
        return 1;
    }


}
