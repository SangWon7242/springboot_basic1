package com.sbs.basic1.boudedContext.member.controller;

import com.sbs.basic1.boudedContext.base.rq.Rq.Rq;
import com.sbs.basic1.boudedContext.base.rsData.RsData;
import com.sbs.basic1.boudedContext.member.entity.Member;
import com.sbs.basic1.boudedContext.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@AllArgsConstructor
@Controller
public class MemberController {
  private final MemberService memberService;
  private final Rq rq;

  @GetMapping("/member/login")
  @ResponseBody
  public RsData login(String username, String password) {

    if (username == null || username.isBlank()) {
      return RsData.of("F-1", "아이디를 입력해주세요.");
    }

    if (password == null || password.isBlank()) {
      return RsData.of("F-2", "비밀번호를 입력해주세요.");
    }
    
    RsData rsData = memberService.tryLogin(username, password);

    if(rsData.isSuccess()) {
      Member member = (Member) rsData.getData();
      rq.setCookie("loginedMemberId", member.getId());
    }

    return rsData;
  }

  @GetMapping("/member/logout")
  @ResponseBody
  public RsData logout() {
    boolean cookieRemoved = rq.removeCookie("loginedMemberId");

    if(!cookieRemoved) {
      return RsData.of("F-1", "로그아웃에 실패하였습니다. 이미 로그아웃 상태입니다.");
    }

    return RsData.of("S-1", "로그아웃 되었습니다.");
  }

  @GetMapping("/member/me")
  @ResponseBody
  public RsData showMe() {
    long loginedMemberId = rq.getCookieAsLong("loginedMemberId", 0L);
    boolean isLogined = loginedMemberId > 0;

    if(!isLogined) {
      return RsData.of("F-1", "로그인 후 이용해주세요.");
    }

    Member member = memberService.findById(loginedMemberId);

    return RsData.of("S-1", "당신의 username(은)는 %s 입니다.".formatted(member.getUsername()));
  }
}
