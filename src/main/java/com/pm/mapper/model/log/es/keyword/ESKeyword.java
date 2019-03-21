package com.pm.mapper.model.log.es.keyword;

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

@Document(indexName = "keyword", type = "keyword", shards = 1, replicas = 0)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ESKeyword extends ESHelper {

    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String keyword;

    @Field(type = FieldType.Text)
    private String user;

    @CreationTimestamp
    @Field(type = Date)
    private Date date;

    public ESKeyword(String keyword, String user){
        this.id = getHashId(keyword, user);
        this.keyword = keyword;
        this.user = user;
        this.date = new Date();
    }


}
