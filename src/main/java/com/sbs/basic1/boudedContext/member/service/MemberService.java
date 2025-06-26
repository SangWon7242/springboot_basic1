package com.sbs.basic1.boudedContext.member.service;

import com.sbs.basic1.boudedContext.base.rsData.RsData;
import com.sbs.basic1.boudedContext.member.entity.Member;
import com.sbs.basic1.boudedContext.member.repository.MemberRepository;

public class MemberService {
  private MemberRepository memberRepository;

  public MemberService() {
    memberRepository = new MemberRepository();
  }

  public RsData tryLogin(String username, String password) {
    Member member = memberRepository.findByUsername(username);

    if(member == null) {
      return RsData.of("F-3", "%s(은)는 존재하지 않는 아이디입니다.".formatted(username));
    }

    if(!member.getPassword().equals(password)) {
      return RsData.of("F-4", "비밀번호가 일치하지 않습니다.");
    }

    return RsData.of("S-1", "'%s'님 로그인 성공하였습니다.".formatted(username));
  }
}
