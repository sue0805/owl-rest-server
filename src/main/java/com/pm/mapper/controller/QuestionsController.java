package com.pm.mapper.controller;

import com.pm.mapper.model.member.MemberScrap;
import com.pm.mapper.model.sortedQuestion.SortedQuestion;
import com.pm.mapper.model.sortedQuestion.SortedQuestionReply;
import com.pm.mapper.model.unsolvedQuestion.UnsolvedQuestion;
import com.pm.mapper.model.unsolvedQuestion.UnsolvedQuestionReply;
import com.pm.mapper.service.member.MemberScrapService;
import com.pm.mapper.service.member.MemberService;
import com.pm.mapper.service.sortedQuestion.SortedQuestionReplyService;
import com.pm.mapper.service.sortedQuestion.SortedQuestionService;
import com.pm.mapper.service.unsolvedQuestion.UnsolvedQuestionReplyService;
import com.pm.mapper.service.unsolvedQuestion.UnsolvedQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/question")
public class QuestionsController {

    @Autowired
    private UnsolvedQuestionService unsolvedQuestionService;

    @Autowired
    private UnsolvedQuestionReplyService unsolvedQuestionReplyService;

    @Autowired
    private SortedQuestionService sortedQuestionService;

    @Autowired
    private SortedQuestionReplyService sortedQuestionReplyService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberScrapService scrapService;

/* =============================== 미해결 질문 ======================================== */

    // 미해결 질문 등록
    @PostMapping("/unsolved")
    public UnsolvedQuestion insertQuestion(@RequestBody UnsolvedQuestion unsolvedQuestion, @RequestParam("mem_idx") long mem_idx){
        unsolvedQuestion.setMember(memberService.findOne(mem_idx));
        unsolvedQuestion.setDate(new Date());
        return unsolvedQuestionService.save(unsolvedQuestion);
    }

    // 미해결 질문 랜덤으로 가져오기
    @GetMapping("/unsolved/random")
    public UnsolvedQuestion getRandomQuestion(){
        List<UnsolvedQuestion> list = unsolvedQuestionService.findAll();
        int rand = (int)(Math.random() * list.size());

        UnsolvedQuestion question = unsolvedQuestionService.findQuestion(list.get(rand).getId());
        question.setViews(question.getViews() + 1);
        unsolvedQuestionService.save(question);

        return list.get(rand);
    }

    // 미해결 질문 조회
    @GetMapping("/unsolved/find/{id}")
    public UnsolvedQuestion findQuestion(@PathVariable long id){
        UnsolvedQuestion question = unsolvedQuestionService.findQuestion(id);
        question.setViews(question.getViews() + 1);

        return unsolvedQuestionService.save(question);
    }


    // 미해결 질문 삭제
    @DeleteMapping("/unsolved")
    public int deleteUnsolvedQuestion(@RequestBody UnsolvedQuestion question){
        try{

            List<MemberScrap> slist = scrapService.findBylink("http://localhost:8095/question/unsolved/find/" + question.getId());
            if(slist.size() > 0){
                scrapService.deleteAll(slist);
            }
            unsolvedQuestionService.deleteByQuestion(question);
            return 1;
        } catch (Exception e){
            return 0;
        }
    }

    // 답변 달린 미해결 질문 읽기 (알림 삭제)
    @PostMapping("/alert")
    public int readAlert(@RequestBody LinkedHashMap<String, List<UnsolvedQuestion>> map){

        Iterator<List<UnsolvedQuestion>> iter = map.values().iterator();
        List<UnsolvedQuestion> list = new ArrayList<>();

        while(iter.hasNext()){
            list = iter.next();
            list.forEach(question -> {
                question = unsolvedQuestionService.findQuestion(question.getId());
                question.setAlert(0);
                unsolvedQuestionService.save(question);
            });
        }

        return list.size();
    }

    // 미해결 질문 답변 채택하기
    @GetMapping("/unsolved/select")
    public SortedQuestion selectReply(@RequestParam("id") long id, @RequestParam("reply_id") long reply_id){
        UnsolvedQuestionReply reply = unsolvedQuestionReplyService.findOne(reply_id);
        reply.setSelected(1);
        unsolvedQuestionReplyService.insertOne(reply);

        UnsolvedQuestion tmp = unsolvedQuestionService.findQuestion(id);

        SortedQuestion question = new SortedQuestion();
        question.setMember(tmp.getMember());
        question.setViews(tmp.getViews());
        question.setContent(tmp.getContent());
        question.setDate(tmp.getDate());
        question.setTags(tmp.getTags());
        question.setTitle(tmp.getTitle());
        question.setWriter(tmp.getWriter());

        SortedQuestion sq = sortedQuestionService.save(question);

        List<SortedQuestionReply> list = new ArrayList<>(tmp.getReplies().size());

        tmp.getReplies().forEach(re -> {
            SortedQuestionReply sortedRe = new SortedQuestionReply();
            sortedRe.setSortedQuestion(sq);
            sortedRe.setMember(re.getMember());
            sortedRe.setReply_content(re.getReply_content());
            sortedRe.setReply_writer(re.getReply_writer());
            sortedRe.setReplyDate(re.getReplyDate());
            sortedRe.setSelected(re.getSelected());
            sortedRe.setUpdateDate(re.getUpdateDate());

            sortedRe = sortedQuestionReplyService.insertOne(sortedRe);
            list.add(sortedRe);
        });

        sq.setReplies(list);

        unsolvedQuestionService.deleteByQuestion(tmp);


        List<MemberScrap> slist = scrapService.findBylink("http://localhost:8095/question/unsolved/find/" + id);
        System.out.println(slist);
        if(slist.size() > 0){
            slist.forEach(scrap -> {
                scrap.setLink("http://localhost:8095/question/sorted?id=" + sq.getId());
            });
        }
        scrapService.saveAll(slist);

        return sq;

    }

    // 미해결 질문 답변 등록하기
    @PostMapping("/unsolved/reply")
    public UnsolvedQuestionReply insert(@RequestBody UnsolvedQuestionReply reply, @RequestParam("mem_idx") long mem_idx, @RequestParam("id") long id){

        UnsolvedQuestion question = unsolvedQuestionService.findQuestion(id);
        question.setAlert(1);
        unsolvedQuestionService.save(question);

        reply.setMember(memberService.findOne(mem_idx));
        reply.setUnsolvedQuestion(unsolvedQuestionService.findQuestion(id));
        reply.setReplyDate(new Date());

        return unsolvedQuestionReplyService.insertOne(reply);
    }

    // 특정 미해결 질문의 답변 리스트
    @PostMapping ("/unsolved/reply/list")
    public List<UnsolvedQuestionReply> getList(
            @RequestBody UnsolvedQuestion unsolvedQuestion
    ){
        return unsolvedQuestionReplyService.getList(unsolvedQuestion);
    }

    // 미해결 질문의 답변 삭제
    @DeleteMapping("/unsolved/reply")
    public int deleteUnsolvedQuestionReply(@RequestBody UnsolvedQuestionReply reply){
        try{
            unsolvedQuestionReplyService.deleteByReply(reply);
            return 1;
        } catch (Exception e){
            return 0;
        }
    }

/* ================================ 해결된 질문 ====================================*/

    // 해결된 질문 리스트 (검색 키워드 기반)
    @GetMapping("/sorted/list")
    public List<SortedQuestion> getQuestionList(@RequestParam("keyword")String keyword, @RequestParam("page") int page){
        return sortedQuestionService.findAllByKeywordWithPaging(keyword, page);
    }

    // 해결된 질문 하나 조회
    @GetMapping("/sorted")
    public SortedQuestion getSortedQuestion(@RequestParam("id") long id){

        SortedQuestion question = sortedQuestionService.findOne(id);
        question.setViews(question.getViews() + 1);

        return sortedQuestionService.save(question);
    }

    // 해결된 질문 수정
    @PostMapping("/sorted")
    public SortedQuestion save(@RequestBody SortedQuestion question, @RequestParam("mem_idx") long mem_idx){
        question.setMember(memberService.findOne(mem_idx));
        return sortedQuestionService.save(question);
    }

    // 해결된 질문 삭제
    @DeleteMapping("/sorted")
    public int deleteSortedQuestion(@RequestBody SortedQuestion question){
        try{

            List<MemberScrap> slist = scrapService.findBylink("http://localhost:8095/question/sorted?id=" + question.getId());

            if(slist.size() > 0){
                scrapService.deleteAll(slist);
            }
            sortedQuestionService.deleteByQuestion(question);

            return 1;

        } catch (Exception e){

            return 0;

        }
    }

    // 해결된 질문 답변 등록
    @PostMapping("/sorted/reply")
    public SortedQuestionReply insert(@RequestBody SortedQuestionReply reply, @RequestParam("mem_idx") long mem_idx, @RequestParam("id") long id){
        reply.setMember(memberService.findOne(mem_idx));
        reply.setSortedQuestion(sortedQuestionService.findOne(id));
        reply.setReplyDate(new Date());

        return sortedQuestionReplyService.insertOne(reply);
    }

    // 특정 해결된 질문 답변 리스트
    @PostMapping("/sorted/reply/list")
    public List<SortedQuestionReply> getList(@RequestBody SortedQuestion sortedQuestion){
        sortedQuestion = sortedQuestionService.findOne(sortedQuestion.getId());
        return sortedQuestionReplyService.getList(sortedQuestion);
    }

    // 해결된 질문의 답변 삭제
    @DeleteMapping("/sorted/reply")
    public int deleteSortedQuestionReply(@RequestBody SortedQuestionReply reply){
        try{
            sortedQuestionReplyService.deleteByReply(reply);
            return 1;
        } catch (Exception e){
            return 0;
        }
    }

    // 해결된 질문 (인기글 조건) 리스트
    @GetMapping("/sorted/hot")
    public List<SortedQuestion> getHotSelect(){
        return sortedQuestionService.findAllOrderByViews();
    }

    // 해결된 질문 조회수 증가
    @PostMapping("/sorted/views/{idx}")
    public SortedQuestion updateView(@PathVariable("idx") long idx){
        //sortedQuestion의 idx에 맞는 해결된 질문을 가져온다.
        SortedQuestion question = sortedQuestionService.findOne(idx);
        //가져온 질문에 조회수를 증가시킨다.
        question.setViews(question.getViews() + 1);

        return sortedQuestionService.save(question);
    }


}
