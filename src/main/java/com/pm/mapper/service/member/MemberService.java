package com.pm.mapper.service.member;

import com.pm.mapper.model.member.Member;
import com.pm.mapper.pojo.JwtHelper;
import com.pm.mapper.repository.member.MemberRepository;
import com.pm.mapper.service.review.ReviewService;
import com.pm.mapper.service.sortedQuestion.SortedQuestionReplyService;
import com.pm.mapper.service.sortedQuestion.SortedQuestionService;
import com.pm.mapper.service.unsolvedQuestion.UnsolvedQuestionReplyService;
import com.pm.mapper.service.unsolvedQuestion.UnsolvedQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MemberService extends JwtHelper {

    @Autowired
    private MemberRepository memberRepository;

    public Member findOne(long idx){return memberRepository.getOne(idx);}


    //회원가입 메세지 보이기
    public String signUp(String email, String nickname, String password) {
        String message = "";
        Member member = new Member();
        if(getMemberByEmail(email)!=null) {
            message = "이미 가입된 이메일이 있습니다";
        }else{
            member.setEmail(email);
            member.setNickname(nickname);
            member.setPassword(password);
            memberRepository.save(member);
            message = "가입 완료";
        }
        return message;
    }

    public Map signIn(String email, String password) {
        Member member = getMemberByEmail(email);
        Map map = new HashMap();
        map.put("message", member == null ? "Wrong E-mail" : !member.getPassword().equals(password) ? "Wrong Password" : "로그인 완료");
        if(map.get("message").equals("로그인 완료")){
            map.put("member", member);
            map.put("accessToken", getJWTTokenFromMember((int)member.getIdx(), member.getNickname(), member.getEmail()));
        }
        return map;
    }

    public Member update(Member member){
        return memberRepository.save(member);
    }

    public void delete(Member member){
        memberRepository.delete(member);
    }

    public Member getMemberByEmail(String email){
        return memberRepository.findByEmail(email);
    }
}
