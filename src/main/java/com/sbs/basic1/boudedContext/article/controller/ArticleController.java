package com.sbs.basic1.boudedContext.article.controller;

import com.sbs.basic1.boudedContext.article.entity.Article;
import com.sbs.basic1.boudedContext.article.repository.ArticleRepository;
import com.sbs.basic1.boudedContext.base.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor // 필드 중에서 final로 선언된 필드에 대해 생성자를 자동으로 생성
public class ArticleController {
  private final ArticleRepository articleRepository;

  @GetMapping("/write")
  @ResponseBody
  public RsData write(String title, String content) {
    Article article = Article.builder()
        .createDate(LocalDateTime.now())
        .modifiedDate(LocalDateTime.now())
        .title(title)
        .content(content)
        .build();

    // 만약 생성자 방식으로 작성하고 싶다면
    // Article article = new Article(title, content);

    /*
    Article article = new Article();
    article.setTitle(title);
    article.setContent(content);
    */

    articleRepository.save(article);

    return RsData.of("S-1", "1번 글이 생성되었습니다.", article);
  }
}
