package com.sbs.basic1.boudedContext.member.controller;

import com.sbs.basic1.boudedContext.base.rsData.RsData;
import com.sbs.basic1.boudedContext.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {
  private final MemberService memberService;

  public MemberController() {
    memberService = new MemberService();
  }

  @GetMapping("/member/login")
  @ResponseBody
  public RsData login(String username, String password) {
    if (username == null || username.isBlank()) {
      return RsData.of("F-1", "아이디를 입력해주세요.");
    }

    if (password == null || password.isBlank()) {
      return RsData.of("F-2", "비밀번호를 입력해주세요.");
    }
    
    RsData tryLogin = memberService.tryLogin(username, password);

    return tryLogin;
  }
}
