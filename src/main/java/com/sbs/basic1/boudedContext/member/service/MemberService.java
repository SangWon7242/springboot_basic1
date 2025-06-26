package com.sbs.basic1.boudedContext.member.service;

import com.sbs.basic1.boudedContext.base.rsData.RsData;

public class MemberService {
  public RsData tryLogin(String username, String password) {
    if(!username.equals("user1")) {
      return RsData.of("F-3", "%s(은)는 존재하지 않는 아이디입니다.".formatted(username));
    }
    else if(!password.equals("1234")) {
      return RsData.of("F-4", "비밀번호가 일치하지 않습니다.");
    }

    return RsData.of("S-1", "'%s'님 로그인 성공하였습니다.".formatted(username));
  }
}
