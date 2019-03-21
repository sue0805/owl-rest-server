package com.pm.mapper.log.es;

import com.pm.mapper.MapperApplicationTest;
import com.pm.mapper.model.log.es.keyword.ESEtcKeyword;
import com.pm.mapper.service.log.es.keyword.ESKeywordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class keyword extends MapperApplicationTest {

//    @Autowired
//    private ElasticsearchTemplate esTemplate;
//
//    @Autowired
//    private ESKeywordService eskeywordService;
//
//    @Test
//    public void testCreateIndex() {
//        esTemplate.createIndex(ESEtcKeyword.class);
//        esTemplate.putMapping(ESEtcKeyword.class);
//    }
//
//    @Test
//    public void testDeleteIndex() {
//        esTemplate.deleteIndex(ESEtcKeyword.class);
//    }
//
//    @Test
//    public void insertKeywordLog(){
//        eskeywordService.insertKeywordLog("testKeyword", "testUser");
//    }
//
//    @Test
//    public void insertKeywordLogList(){
//        List list = new ArrayList();
//        for (int i = 0; i <10; i++) {
//            list.add("testKeyword"+i);
//        }
//        eskeywordService.insertKeywordLogList(list, "testUser");
//    }
//
//    @Test
//    public void selectAll(){
//        Iterable<ESEtcKeyword> list = eskeywordService.selectAll();
//        for(ESEtcKeyword esEtcKeyword : list){
//            System.out.println(esEtcKeyword);
//        }
//    }
}
