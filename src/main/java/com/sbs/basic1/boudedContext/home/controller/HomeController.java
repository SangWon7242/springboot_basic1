package com.sbs.basic1.boudedContext.home.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 스프링부트한테 이 클래스가 컨트롤러임을 알려줌
public class HomeController {
  int no;

  public HomeController() {
    no = -1;
  }


  @GetMapping("/home/main") // /home/main 주소로 GET 요청이 오면 이 메서드가 실행됨
  @ResponseBody // 이 메서드의 실행된 결과를 body에 담아서 응답으로 보냄
  public String showMain() {
    return "안녕하세요, 홈 페이지입니다.";
  }

  @GetMapping("/home/main2")
  @ResponseBody
  public String showMain2() {
    return "어서오세요. 홈 페이지2입니다.";
  }

  @GetMapping("/home/main3")
  @ResponseBody
  public String showMain3() {
    return "반갑습니다. 홈 페이지3입니다.";
  }

  @GetMapping("/home/plus")
  @ResponseBody
  public int showPlus(@RequestParam(defaultValue = "0") int a, int b) {
    /*
    // HttpServletRequest와 HttpServletResponse를 사용하여 요청 파라미터를 가져오는 방법
    int a = Integer.parseInt(req.getParameter("a"));
    int b = Integer.parseInt(req.getParameter("b"));
    return a + b;
    */
    
    // @RequestParam은 쿼리 파라미터를 매개변수로 전달받는다.
    // @RequestParam은 생략 가능하다.
    // @RequestParam을 생략하는 경우 쿼리 파라미터와 매개변수 이름은 일치해야 한다.
    
    // 파라미터에 기본값을 주는 경우 @RequestParam(defaultValue = "0") 을 통해 기본값 지정 가능
    
    return a + b;
  }

  @GetMapping("/home/gugudan")
  @ResponseBody
  public String showGugudan(@RequestParam(defaultValue = "9") int dan, @RequestParam(defaultValue = "9") int limit) {
    StringBuilder sb = new StringBuilder();
    sb.append("<h1>== 구구단 %d단 ==</h1>\n".formatted(dan));

    for (int i = 1; i <= limit; i++) {
      // sb.append 는 문자열을 연결
      sb.append("<div>%d * %d = %d</div>\n".formatted(dan, i, dan * i));
    }
    
    // sb.toString() 는 StringBuilder 객체를 문자열(String 타입)로 변환
    return sb.toString();
  }

  @GetMapping("/home/gugudan2/{dan}/{limit}")
  @ResponseBody
  public String showGugudan2(@PathVariable int dan, @PathVariable int limit) {
    StringBuilder sb = new StringBuilder();
    sb.append("<h1>== 구구단 %d단 ==</h1>\n".formatted(dan));

    for (int i = 1; i <= limit; i++) {
      // sb.append 는 문자열을 연결
      sb.append("<div>%d * %d = %d</div>\n".formatted(dan, i, dan * i));
    }

    // sb.toString() 는 StringBuilder 객체를 문자열(String 타입)로 변환
    return sb.toString();
  }
}
