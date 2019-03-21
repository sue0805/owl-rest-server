package com.pm.mapper.model.review;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pm.mapper.model.member.Member;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="review")
@Component
@Getter
@Setter
@ToString(exclude = "member")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String star;
    private String keyword;
    private String review;
    private String writer;
    @CreationTimestamp
    private Date date;

    @ManyToOne
    @JoinColumn(name = "mem_idx")
    @JsonIgnoreProperties({"reviews", "sortedQuestions", "unsolvedQuestions", "memberScraps", "unsolvedQuestionReplies", "sortedQuestionReplies" ,"password"})
    private Member member;

}
