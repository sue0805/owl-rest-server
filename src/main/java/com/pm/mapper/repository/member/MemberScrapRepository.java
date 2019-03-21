package com.pm.mapper.repository.member;

import com.pm.mapper.model.member.Member;
import com.pm.mapper.model.member.MemberScrap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberScrapRepository extends JpaRepository<MemberScrap, Long> {

    List<MemberScrap> findAllByLinkAndMember(String link, Member member);

    List<MemberScrap> findAllByLink(String link);
}
