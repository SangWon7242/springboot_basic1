package com.sbs.basic1.boudedContext.member.service;

import com.sbs.basic1.boudedContext.base.rsData.RsData;
import com.sbs.basic1.boudedContext.member.entity.Member;
import com.sbs.basic1.boudedContext.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service // 스프링부트한테 이 클래스가 서비스 클래스임을 알려줌
// 아래 클래스는 Ioc 컨테이너에 등록되어 관리됨
// @Service 어노테이션을 사용하면 @Component 어노테이션이 붙은 클래스와 동일하게 동작함
// @Component
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;

  public RsData tryLogin(String username, String password) {
    Member member = memberRepository.findByUsername(username).orElse(null);

    if(member == null) {
      return RsData.of("F-3", "%s(은)는 존재하지 않는 아이디입니다.".formatted(username));
    }

    if(!member.getPassword().equals(password)) {
      return RsData.of("F-4", "비밀번호가 일치하지 않습니다.");
    }

    return RsData.of("S-1", "'%s'님 로그인 성공하였습니다.".formatted(username), member);
  }

  public Member findByUsername(String username) {
    return memberRepository.findByUsername(username).orElse(null);
  }

  public Member findById(long id) {
    return memberRepository.findById(id).orElse(null);
  }

  public void join(String username, String password) {
    Member member = Member.builder()
        .username(username)
        .password(password)
        .build();

    memberRepository.save(member);
  }
}
