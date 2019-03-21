package com.pm.mapper.model.member;

import com.pm.mapper.repository.member.MemberRepository;
import com.pm.mapper.repository.member.MemberScrapRepository;
import com.pm.mapper.service.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MemberScrapTest {

    @Autowired
    Member member;

    @Autowired
    private MemberScrap memberScrap;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberScrapRepository memberScrapRepository;

    @Autowired
    private MemberService memberService;

    //memberScrap null아닌 지 확인
    @Test
    public void testMemberScrapTest_NotNull(){
        assertNotNull(memberScrap);
    } //통과

    //memberScrap Insert Test
    @Test
    public void testMemberScrapInsertOne(){
        memberScrap.setLink("http://localhost:8095/api/sortedQuestion/1773");
        memberScrap.setTitle("테스트 제목");
        memberScrap.setMember(memberRepository.getOne(62L));
        memberScrapRepository.save(memberScrap);
        Assert.assertNotNull(memberRepository);
        memberScrapRepository.delete(memberScrap);
    }
    //통과

    //findOne Test
    @Test
    public void testFindOne(){
        memberScrapRepository.getOne(13L);
    }
    //통과

    //delete by link and Member Test
    @Test
    public void testDeleteByLinkAndMember(){
        List<MemberScrap> list = memberScrapRepository.findAllByLinkAndMember(memberScrap.getLink(), memberService.findOne(62L));
        memberScrapRepository.deleteAll(list);
    }
    //통과

    //findBylink Test
    @Test
    public void testFindByLisk(){
        List<MemberScrap> scraps = memberScrapRepository.findAllByLink("http://localhost:8095/api/sortedQuestion/1773");
        Assert.assertNotNull(scraps );
    }//통과





}
