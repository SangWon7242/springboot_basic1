package com.sbs.basic1.boudedContext.member.repository;

import com.sbs.basic1.boudedContext.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

  Optional<Member> findByUsername(String username);
}
