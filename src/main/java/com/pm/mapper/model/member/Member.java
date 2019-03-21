package com.pm.mapper.model.member;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pm.mapper.model.review.Review;
import com.pm.mapper.model.sortedQuestion.SortedQuestion;
import com.pm.mapper.model.sortedQuestion.SortedQuestionReply;
import com.pm.mapper.model.unsolvedQuestion.UnsolvedQuestion;
import com.pm.mapper.model.unsolvedQuestion.UnsolvedQuestionReply;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//entity를 생성한다. 객체를 생성한다.
@Entity
//테이블 이름을 지정한다.
@Table(name = "member")
@Component
@Getter
@Setter
@ToString(exclude = "reviews")
public class Member{
    //해당 프로퍼티가 테이블의 주키(primary key)역할을 한다.
    @Id
    //자동 생성전략 증가하면서 id값을 따로 지정안해줘도 숫자가 증가하면서 id값을 만들어준다.
    //DB의 identity컬럼을 이용한다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mem_idx")
    private long idx;
    private String email;
    private String password;
    private String nickname;
    //시간 설정을 알아서 해준다.
    @CreationTimestamp
    private Date regdate;

    public  Member(){

    }

    public Member(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    //1 : N 관계
    @OneToMany(mappedBy = "member", orphanRemoval = true)
    //외래키를 맵핑할 때 사용 , name 매핑할 외래키 이름
    //member는 제외된다. 없으면 계속 만들어진다.
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "member", orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<SortedQuestion> sortedQuestions = new ArrayList<>();

    @OneToMany(mappedBy = "member", orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<UnsolvedQuestion> unsolvedQuestions = new ArrayList<>();

    @OneToMany(mappedBy = "member", orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<MemberScrap> memberScraps = new ArrayList<>();

    //댓글 테이블과 관계설정
    //1 대 다 관계
    @OneToMany(mappedBy = "member", orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<UnsolvedQuestionReply> unsolvedQuestionReplies = new ArrayList<>();

    @OneToMany(mappedBy = "member", orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<SortedQuestionReply> sortedQuestionReplies = new ArrayList<>();

}
