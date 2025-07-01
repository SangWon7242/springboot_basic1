package com.sbs.basic1.boudedContext.base.rq.Rq;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Arrays;

@AllArgsConstructor
@Component
@RequestScope // 객체는 매 요청마다 생성
public class Rq {
  private HttpServletRequest req;
  private HttpServletResponse resp;

  public void setCookie(String name, long value) {
    setCookie(name, value + "");
  }

  public void setCookie(String name, String value) {
    resp.addCookie(new Cookie(name, value));
  }

  public boolean removeCookie(String name) {
    Cookie cookie = Arrays.stream(req.getCookies())
        .filter(c -> c.getName().equals(name))
        .findFirst()
        .orElse(null);

    if (cookie != null) {
      cookie.setMaxAge(0); // 쿠키를 삭제하기 위해 만료 시간을 0으로 설정
      resp.addCookie(cookie); // 변경된 쿠키를 응답에 추가
      return true; // 쿠키가 성공적으로 삭제됨
    }

    return false;
  }

  public long getCookieAsLong(String name, long defaultValue) {
    String value = getCookie(name, null);

    if (value == null) return defaultValue;

    try {
      return Long.parseLong(value);
    } catch (NumberFormatException e) {
      return defaultValue;
    }

  }

  private String getCookie(String name, String defaultValue) {
    if (req.getCookies() == null) return defaultValue;

    return Arrays.stream(req.getCookies())
        .filter(cookie -> cookie.getName().equals(name))
        .map(Cookie::getValue)
        .findFirst()
        .orElse(defaultValue);
  }
}
