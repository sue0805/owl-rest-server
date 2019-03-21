package com.pm.mapper.model.unsolvedQuestion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pm.mapper.model.member.Member;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


//테이블 객체를 생성한다.
@Entity
//테이블 이름 설정한다.
@Table(name = "unsolved_question")
@Component
@Getter
@Setter
public class UnsolvedQuestion {

    //pk지정
    @Id
    //자동 생성 전략 사용
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String writer;
    private String title;
    private String content;
    private String tags;
    //날짜 자동 생성 .지금 날짜로
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @ColumnDefault("0")
    private int alert;
    @ColumnDefault("0")
    private int views;

    //N : 1관계
    @ManyToOne
    //mem_idx기준으로 memeber table과 join한다.
    @JoinColumn(name = "mem_idx")
    @JsonIgnoreProperties({"reviews", "sortedQuestions", "unsolvedQuestions", "memberScraps", "unsolvedQuestionReplies", "sortedQuestionReplies" ,"password"})
    private Member member;

    @OneToMany(mappedBy = "unsolvedQuestion", orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<UnsolvedQuestionReply> replies;

    public UnsolvedQuestion(String writer, String title, String content, String tags) {
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.tags = tags;
    }

    public UnsolvedQuestion(){ super(); }
}
