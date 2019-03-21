package com.pm.mapper.service.member;

import com.pm.mapper.model.member.MemberScrap;
import com.pm.mapper.repository.member.MemberScrapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberScrapService {

    @Autowired
    private MemberScrapRepository repository;

    @Autowired
    private MemberService memberService;

    public MemberScrap insertOne(MemberScrap scrap){

        return repository.save(scrap);

    }

    public MemberScrap findOne(long idx){
        return repository.getOne(idx);
    }

    public void delete(MemberScrap scrap, long mem_idx){

        List<MemberScrap> list = repository.findAllByLinkAndMember(scrap.getLink(), memberService.findOne(mem_idx));

        repository.deleteAll(list);
    }

    public List<MemberScrap> findBylink(String link){return repository.findAllByLink(link);}

    public void delete(MemberScrap scrap){repository.delete(scrap);}

    public void deleteAll(List<MemberScrap> list){repository.deleteAll(list);}

    public void saveAll(List<MemberScrap> list){repository.saveAll(list);}

}
