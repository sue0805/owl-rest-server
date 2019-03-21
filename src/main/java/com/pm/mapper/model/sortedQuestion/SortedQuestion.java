package com.pm.mapper.model.sortedQuestion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pm.mapper.model.member.Member;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sorted_question")
@Component
@Getter
@Setter
public class SortedQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String writer;
    private String title;
    private String content;
    private String tags;
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @ColumnDefault("0")
    private long views;

    @OneToMany(mappedBy = "sortedQuestion", orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<SortedQuestionReply> replies;

    @ManyToOne
    @JoinColumn(name = "mem_idx")
    @JsonIgnoreProperties({"reviews", "sortedQuestions", "unsolvedQuestions", "memberScraps", "unsolvedQuestionReplies", "sortedQuestionReplies" ,"password"})
    private Member member;

    public SortedQuestion(String writer, String title, String content, String tags) {
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.tags = tags;
    }

    public SortedQuestion(String writer, String title, String content, String tags, Date date) {
        this.id = 1;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.date = date;
    }

    public SortedQuestion(){ super(); }
}
