package com.pm.mapper.test;

import com.pm.mapper.model.member.Member;
import com.pm.mapper.model.sortedQuestion.SortedQuestion;
import com.pm.mapper.model.sortedQuestion.SortedQuestionReply;
import com.pm.mapper.model.unsolvedQuestion.UnsolvedQuestion;
import com.pm.mapper.model.unsolvedQuestion.UnsolvedQuestionReply;
import com.pm.mapper.service.member.MemberService;
import com.pm.mapper.service.sortedQuestion.SortedQuestionReplyService;
import com.pm.mapper.service.sortedQuestion.SortedQuestionService;
import com.pm.mapper.service.unsolvedQuestion.UnsolvedQuestionReplyService;
import com.pm.mapper.service.unsolvedQuestion.UnsolvedQuestionService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OkkyTest {
//    https://okky.kr/article/559098

    @Autowired
    MemberService memberService;

    @Autowired
    UnsolvedQuestionService unsolvedQuestionService;

    @Autowired
    UnsolvedQuestionReplyService unsolvedQuestionReplyService;

    @Autowired
    SortedQuestionService sortedQuestionService;

    @Autowired
    SortedQuestionReplyService sortedQuestionReplyService;

    @Test
    public void readOkky(){
        Document doc = null;
        Elements els = null;
        String connUrl = "https://okky.kr/article/";
        int a = 0;

        for(int j = 462132 ; j < 557000 ; j++) {
        StringBuffer conts = new StringBuffer();

            a = j;


            int[] memArr = {1, 2, 3, 4, 5, 29, 30, 31, 32, 39, 46, 47, 48};
            String[] nickArr = {"testnm1", "testnm2", "testnm3", "test1", "test2", "Wickies", "이양헌", "WickiesLee", "test12345", "SSUE", "test1111", "IAN", "yongood"};

            try {

                SortedQuestion question = new SortedQuestion();
                int rand1 = (int) (Math.random() * memArr.length);

                Member member = memberService.findOne(memArr[rand1]);

                question.setMember(member);
                question.setWriter(nickArr[rand1]);

                doc = Jsoup.connect(connUrl + j).maxBodySize(Integer.MAX_VALUE).ignoreContentType(true).get();


                question.setTitle(doc.select("#content-body > h2").text());

                String date = doc.select("#article > div.panel.panel-default.clearfix.fa- > div.panel-heading.clearfix > div.avatar.avatar-medium.clearfix.pull-left > div > div.date-created > span.timeago").text();

                question.setDate(java.sql.Timestamp.valueOf(date));

                List<String> contents = doc.select("#content-body > article > p, #content-body > article > div").eachText();

                contents.forEach(content -> {

                    conts.append(content + "\n");

                });

                question.setContent(conts.toString());


                question.setTags("#" + doc.select("#content-body > h2").text().replaceAll(" ", "#"));

                if(conts.length() == 0) {
                    String content = doc.select("#content-body > article").text();
                    if(content.length() == 0) continue;
                    else question.setContent(content);
                }

                List<String> replies = doc.select(".list-group > .note-item article").eachText();
                List<String> dates = doc.select(".list-group > .note-item .timeago").eachText();

                if (replies.size() > 0) {
                    question = sortedQuestionService.save(question);

                    for (int i = 0; i < replies.size(); i++) {

                        SortedQuestionReply reply = new SortedQuestionReply();

                        int rand2 = (int) (Math.random() * memArr.length);
                        Member member1 = memberService.findOne(memArr[rand2]);
                        reply.setReply_content(replies.get(i));
                        reply.setReplyDate(java.sql.Timestamp.valueOf(dates.get(i)));
                        reply.setMember(member1);
                        reply.setReply_writer(nickArr[rand2]);
                        reply.setSortedQuestion(question);

                        if(i == 0) reply.setSelected(1);

                        sortedQuestionReplyService.insertOne(reply);

                    }

                    System.out.println( a + " inserted");
                }

            } catch (Exception e) {
                System.out.println( a + " failed");
            }
        }
    }

}
