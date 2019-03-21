package com.pm.mapper.model.member;

import static org.junit.Assert.*;

import com.pm.mapper.repository.member.MemberRepository;
import com.pm.mapper.service.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

//jUnit을 위한 설정
@RunWith(SpringRunner.class)
//로그 남기는 기능
@SpringBootTest
@Slf4j
public class MemberTest {

    //Member클래스에 의존 설정을 해준다.
    @Autowired
    private Member member;

    //멤버 저장소에 의존설정을 해준다.
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    //테스트할 메스드를 지정한다. member의 entity가 존재하는 지 확인
    @Test
    public void testMember(){
        //member가 null이 아닌지 확인한다.
        assertNotNull(member);
        //로그에 기록으로 확인한다.
    }

    //서비스의 회원가입이 되는 지 확인
//    @Test
//    public void testSingUp_NotNull(){
//        member.setEmail("ttt113@tset.com");
//        member.setNickname("testeew");
//        member.setPassword("12422");
//        memberRepository.save(member);
//        Assert.assertNotNull(member);
//    }

    //로그인이 되는 지 확인
//    @Test
//    public void testSingIn_InputData(){
//        String result = "";
//
//        long emailCheck = memberRepository.countByEmail("test@test.com");
//        if(emailCheck == 1){
//            String pwCheck = memberRepository.findByEmail("test@tset.com").getPassword();
//            if(pwCheck.equals("testpwd")){
//                result = "로그인 성공";
//            }else{
//                result = "비밀번호를 확인해주세요";
//            }
//        }else {
//            result = "아이디를 확인해주세요";
//        }
//    }
    //통과

    // member의 email를 찾는 테스트
//    @Test
//    public void testFindEmail() {
//        Member findEmail = memberRepository.findByEmail("ttt111@tset.com");
//        log.info(findEmail.getNickname());
//    }//통과
//

}
