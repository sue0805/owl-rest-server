package com.pm.mapper.model.sortedQuestion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pm.mapper.model.member.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Entity
@Component
@Table(name = "sortedQuestionReply")
@Getter
@Setter
@ToString(exclude = {"member", "sortedQuestion"})
public class SortedQuestionReply {
    // 구성
    // reply의 고유 idx
    //아이디 지정
    @Id
    //자동으로 숫자 증가
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reply_idx;

    // 내용
    private String reply_content;
    private String reply_writer;
    // 날짜
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date replyDate;
    @CreationTimestamp
    private Date updateDate;

    @ColumnDefault("0")
    private int selected;

    //테이블 관계
    //member와 다 : 1관계

    @ManyToOne
    //member의 pk와 연결
    @JoinColumn(name = "mem_idx")
    @JsonIgnoreProperties({"reviews", "sortedQuestions", "unsolvedQuestions", "memberScraps", "unsolvedQuestionReplies", "sortedQuestionReplies", "password"})
    private Member member;

    @ManyToOne
    @JoinColumn(name = "id")
    @JsonIgnoreProperties(value = "replies", allowSetters = true)
    private SortedQuestion sortedQuestion;
}
