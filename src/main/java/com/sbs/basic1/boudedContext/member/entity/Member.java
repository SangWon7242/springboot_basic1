package com.sbs.basic1.boudedContext.member.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Member {
  // Long : null을 허용하는 64비트 정수
  // long : null을 허용하지 않는 64비트 정수
  private static Long lastId;
  private final Long id;
  private String username;
  private String password;

  static {
    lastId = 0L;
  }

  public Member(String usrname, String password) {
    this(++lastId, usrname, password);
  }
}
