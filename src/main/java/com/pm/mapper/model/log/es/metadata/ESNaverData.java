package com.pm.mapper.model.log.es.metadata;

import com.pm.mapper.pojo.ESHelper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

import static org.springframework.data.elasticsearch.annotations.FieldType.Date;

@Document(indexName = "data_naver", type = "data", shards = 1, replicas = 0)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ESNaverData extends ESHelper {

    @Id
    private Long id;

    @Field(type = FieldType.Keyword)
    private String keyword;

    @Field(type = FieldType.Text)
    private String link;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String snippet;

    @CreationTimestamp
    @Field(type = Date)
    private java.util.Date date;

    public ESNaverData(String keyword, String link, String title, String snippet) {
        this.id = getHashId(link);
        this.keyword = keyword;
        this.link = link;
        this.title = title;
        this.snippet = snippet;
        this.date = new Date();
    }
}