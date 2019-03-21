package com.pm.mapper.model.unsolvedQuestion;

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

//UnsolvedQuestion의 댓글 게시판 생성
@SuppressWarnings("ALL")
@Entity
@Component
@Table(name = "UnsolvedQuestion_Reply")
@Getter
@Setter
public class UnsolvedQuestionReply {

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
    @JsonIgnoreProperties({"reviews", "sortedQuestions", "unsolvedQuestions", "memberScraps", "unsolvedQuestionReplies", "sortedQuestionReplies" ,"password"})
    private Member member;
    // unsolvedQuestion과 다:1 관계

    @ManyToOne
    //unsolvedQuesion의 pk와 연결
    @JoinColumn(name = "id")
    @JsonIgnoreProperties(value = "replies", allowSetters = true)
    private UnsolvedQuestion unsolvedQuestion;

    //기본 생성자 생성
    public UnsolvedQuestionReply() {
    }

    //content하나만 넣는 생성자 만들기
    public UnsolvedQuestionReply(String reply_content) {
        this.reply_content = reply_content;
    }

    //content와 writer 넣는 생성자
    public UnsolvedQuestionReply(String reply_writer, String reply_content) {
        this.reply_content = reply_content;
        this.reply_writer = reply_writer;
    }

    //content, writer ,id 의 값을 넣는 생성자
    public UnsolvedQuestionReply(String reply_content, String reply_writer, UnsolvedQuestion id) {
        this.reply_content = reply_content;
        this.reply_writer = reply_writer;
        this.unsolvedQuestion = id;
    }

    //content, writer ,idx ,id의 값을 넣는 생성자
    public UnsolvedQuestionReply(String reply_content, String reply_writer, Member idx, UnsolvedQuestion id) {
        this.reply_content = reply_content;
        this.reply_writer = reply_writer;
        this.member = idx;
        this.unsolvedQuestion = id;
    }
}
