package com.pm.mapper.repository.member;

import com.pm.mapper.model.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);
    long countByEmail(String email);

}
