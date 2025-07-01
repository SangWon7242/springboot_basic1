package com.sbs.basic1.boudedContext.member.controller;

import com.sbs.basic1.boudedContext.base.rsData.RsData;
import com.sbs.basic1.boudedContext.member.entity.Member;
import com.sbs.basic1.boudedContext.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

@AllArgsConstructor
@Controller
public class MemberController {
  private final MemberService memberService;

  @GetMapping("/member/login")
  @ResponseBody
  public RsData login(String username, String password, HttpServletResponse resp) {
    if (username == null || username.isBlank()) {
      return RsData.of("F-1", "아이디를 입력해주세요.");
    }

    if (password == null || password.isBlank()) {
      return RsData.of("F-2", "비밀번호를 입력해주세요.");
    }
    
    RsData rsData = memberService.tryLogin(username, password);

    if(rsData.isSuccess()) {
      long memberId = (long) rsData.getData();
      resp.addCookie(new Cookie("loginedMemberId", memberId + ""));
    }

    return rsData;
  }

  @GetMapping("/member/logout")
  @ResponseBody
  public RsData logout(HttpServletRequest req, HttpServletResponse resp) {
    if(req.getCookies() != null) {
      Arrays.stream(req.getCookies())
          .filter(cookie -> cookie.getName().equals("loginedMemberId"))
          .forEach(e -> {
            e.setMaxAge(0); // 쿠키를 삭제하기 위해 maxAge를 0으로 설정
            resp.addCookie(e);
          }); // 쿠키 삭제
    }

    return RsData.of("S-1", "로그아웃 되었습니다.");
  }

  @GetMapping("/member/me")
  @ResponseBody
  public RsData showMe(String username, String password, HttpServletRequest req, HttpServletResponse resp) {

    long loignedMemberId = 0L;

    if(req.getCookies() != null) {
      loignedMemberId = Arrays.stream(req.getCookies())
          .filter(cookie -> cookie.getName().equals("loginedMemberId"))
          .map(Cookie::getValue)
          .mapToLong(Long::parseLong)
          .findFirst()
          .orElse(0L);
    }

    boolean isLogined = loignedMemberId > 0;

    if(!isLogined) {
      return RsData.of("F-1", "로그인 후 이용해주세요.");
    }

    Member member = memberService.findById(loignedMemberId);

    return RsData.of("S-1", "당신의 username(은)는 %s 입니다.".formatted(member.getUsername()));
  }
}
