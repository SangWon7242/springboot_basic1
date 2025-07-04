package com.sbs.basic1.boudedContext.member.controller;

import com.sbs.basic1.boudedContext.base.rq.Rq.Rq;
import com.sbs.basic1.boudedContext.base.rsData.RsData;
import com.sbs.basic1.boudedContext.member.entity.Member;
import com.sbs.basic1.boudedContext.member.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
  private final MemberService memberService;
  private final Rq rq;

  @GetMapping("/login")
  public String login() {
    return "usr/member/login";
  }

  @PostMapping("/login")
  @ResponseBody
  public RsData login(String username, String password) {

    if (username == null || username.isBlank()) {
      return RsData.of("F-1", "아이디를 입력해주세요.");
    }

    if (password == null || password.isBlank()) {
      return RsData.of("F-2", "비밀번호를 입력해주세요.");
    }

    RsData rsData = memberService.tryLogin(username, password);

    if (rsData.isSuccess()) {
      Member member = (Member) rsData.getData();
      rq.setSession("loginedMemberId", member.getId());
    }

    return rsData;
  }

  @GetMapping("/logout")
  @ResponseBody
  public RsData logout() {
    boolean cookieRemoved = rq.removeSession("loginedMemberId");

    if (!cookieRemoved) {
      return RsData.of("F-1", "로그아웃에 실패하였습니다. 이미 로그아웃 상태입니다.");
    }

    return RsData.of("S-1", "로그아웃 되었습니다.");
  }

  @GetMapping("/me")
  public String showMe(Model model) {
    long loginedMemberId = rq.getLoginedMember();

    Member member = memberService.findById(loginedMemberId);
    model.addAttribute("member", member);

    return "usr/member/me";
  }

  @GetMapping("/session")
  @ResponseBody
  public String showSession() {
    return rq.getSessionDebugInfo().replaceAll("\n", "<br>");
  }
}
