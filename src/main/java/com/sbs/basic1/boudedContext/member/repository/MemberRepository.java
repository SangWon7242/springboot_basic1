package com.sbs.basic1.boudedContext.member.repository;

import com.sbs.basic1.boudedContext.member.entity.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
  private List<Member> members;

  public MemberRepository() {
    members = new ArrayList<>();

    members.add(new Member("user1", "1234"));
    members.add(new Member("user2", "6789"));
    members.add(new Member("alice", "pass1"));
    members.add(new Member("bob", "qwerty"));
    members.add(new Member("charlie", "zxcvbn"));
    members.add(new Member("david", "hello123"));
    members.add(new Member("eve", "password!"));
    members.add(new Member("frank", "letmein"));
    members.add(new Member("grace", "sunshine"));
    members.add(new Member("heidi", "abc123"));
    members.add(new Member("ivan", "trustno1"));
    members.add(new Member("judy", "iloveyou"));
  }

  public Member findByUsername(String username) {
    return members.stream()
        .filter(member -> member.getUsername().equals(username))
        .findFirst()
        .orElse(null);
  }

  public Member findById(long id) {
    return members.stream()
        .filter(member -> member.getId() == id)
        .findFirst()
        .orElse(null);
  }
}
