package com.sbs.basic1.boudedContext.base.initData;

import com.sbs.basic1.boudedContext.article.service.ArticleService;
import com.sbs.basic1.boudedContext.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration // 컴포넌트와 비슷한 역할
@Profile({"dev", "text", "prod"})
// dev : 개발환경
// text : 테스트 환경
// prod : 운영환경
// NotProd : 운영 환경이 아닌 경우에만 실행
@RequiredArgsConstructor
public class NotProd {
  // private final MemberRepository memberRepository;

  @Bean // IoC 컨테이너에 의해 관리
  CommandLineRunner initData(MemberService memberService, ArticleService articleService) {
    return args -> {
      // memberRepository.deleteAll(); --> ddl-auto를 update로 두었을 때만 사용

      // 초기 데이터를 삽입
      memberService.join("user1", "1234");
      memberService.join("user2", "5678");
      memberService.join("abc", "123456");
      memberService.join("test", "1222");
      memberService.join("bbc", "1111");
      memberService.join("love", "5555");
      memberService.join("hello", "123478");
      memberService.join("user3", "33333");
      memberService.join("good", "456732");
      memberService.join("like", "123444");

      articleService.write("제목1", "내용1");
      articleService.write("제목2", "내용2");
    };
  }
}
