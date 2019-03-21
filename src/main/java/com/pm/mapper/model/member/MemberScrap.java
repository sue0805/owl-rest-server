package com.pm.mapper.model.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Table(name = "memberScrap")
@Entity
@Component
@Getter
@Setter
@ToString(exclude = "member")
public class MemberScrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;
    private String title;
    private String link;

    //N : 1 관계
    @ManyToOne
    @JoinColumn(name="mem_idx")
    @JsonIgnoreProperties({"reviews", "sortedQuestions", "unsolvedQuestions", "memberScraps", "unsolvedQuestionReplies", "sortedQuestionReplies" ,"password"})
    private Member member;

}
