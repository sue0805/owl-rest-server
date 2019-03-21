package com.pm.mapper.model.log.es.searchresult;

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

@Document(indexName = "searchresult", type = "searchresult", shards = 1, replicas = 0)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ESSearchResult extends ESHelper {

    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String link;

    @Field(type = FieldType.Text)
    private String div;

    @Field(type = FieldType.Text)
    private String user;

    @CreationTimestamp
    @Field(type = Date)
    private java.util.Date date;

    public ESSearchResult(String title, String link, String div, String user){
        this.id = getHashId(link, user);
        this.title = title;
        this.link = link;
        this.div = div;
        this.user = user;
        this.date = new Date();
    }
}