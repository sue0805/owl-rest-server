package com.pm.mapper.model.sortedQuestion;

import com.pm.mapper.model.member.Member;
import com.pm.mapper.repository.member.MemberRepository;
import com.pm.mapper.repository.sortedQuestion.SortedQuestionRepository;
import com.pm.mapper.service.sortedQuestion.SortedQuestionService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.search.SortedSetSortField;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SortedQuestionTest {

    //Test Table 만들기
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//    private String writer;
//    private String title;
//    private String content;
//    private String tags;
//    @CreationTimestamp
//    private Date date;
//    @ColumnDefault("0")
//    private long views;


    @Autowired
    private SortedQuestionService sortedQuestionService;

    @Autowired
    private SortedQuestionRepository sortedQuestionRepository;

    @Autowired
    private SortedQuestion  sortedQuestion;

    @Autowired
    private Member member;

    @Autowired
    private MemberRepository memberRepository;


    //SortedQuestion Not Null Test
    @Test
    public void testSortedQuestion_NotNull(){
        Assert.assertNotNull(sortedQuestion);
    }
    //통과

    //해결된 질문에 ListAll() Test
    @Test
    public void testGetSortedQuestionList(){
        //해결된 질문의 데이터를 모두 찾는다.
        List list = sortedQuestionRepository.findAll();
        //배열을 result에 담는다.
        List result = new ArrayList();
        for(int i =0; i < 4; i++){
            result.add(list.get(i));
        }
        Assert.assertNotNull(result);
    }
    //통과

    //페이징 처리를 활용한 해결된 질문 모두 찾기
//    @Test
//    public void testFindAllWithPaging(){
//        //id를 기준으로 내림차순한다. 페이지0,사이즈10
//        PageRequest pageRequest = PageRequest.of(0,10, Sort.Direction.DESC,"id");
//        //페이지 타입으로 결과를 담는다. 저장소에서 PageRequest에 맞게 가져온 모든 데이터를 Page의 result에 담는다.
//        Page<SortedQuestion> result = sortedQuestionRepository.findAll(pageRequest);
//        Assert.assertNotNull(result);
//    }
    //통과

    //키워드 위주로 해결된 질문 모두 찾아서 5개 페이징 처리
    @Test
    public void testFindAllByKeywordWithPaging(){
        PageRequest pageRequest = PageRequest.of(0,20,new Sort(Sort.Direction.DESC, "date"));
        Page<SortedQuestion> result = sortedQuestionRepository.findAllByTagsContainsIgnoreCase("Java", pageRequest);
        Assert.assertNotNull(result);
    }
    //통과

    //인기 게시글 findAll Test
    @Test
    public void testGetQuestionList(){
        List list = sortedQuestionRepository.findAll();
        List result = new ArrayList();
        for(int i = 0; i < 4; i++){
            result.add(list.get(i));
        }
        Assert.assertNotNull(result);
    }
    //통과

    //findOne Test
//    @Test
//    public void testFindOne(){
//        SortedQuestion getOne = sortedQuestionRepository.getOne(232054L);
//        Assert.assertNotNull(getOne);
//        log.info(getOne.toString());
//    }

    //findAllOrderByViews메서드 Test
    @Test
    public void testFindOrderByViews(){
        List<SortedQuestion> list = new ArrayList<>();
        list = sortedQuestionRepository.findAllDateAfter7Day();
        Assert.assertNotNull(list);
    }
    //통과

    //save메서드 Test
//    @Test
//    public void testSave(){
//        sortedQuestion.setContent("테스트 입력");
//        sortedQuestion.setDate(new Date());
//        sortedQuestion.setTags("Test");
//        sortedQuestion.setTitle("테스트 제목");
//        sortedQuestion.setWriter("언어");
//        sortedQuestion.setMember(memberRepository.getOne(2L));
//        sortedQuestionRepository.save(sortedQuestion);
//        sortedQuestionRepository.delete(sortedQuestion);
//    }
    //통과

}
