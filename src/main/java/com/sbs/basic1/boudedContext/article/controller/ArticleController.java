package com.sbs.basic1.boudedContext.article.controller;

import com.sbs.basic1.boudedContext.article.entity.Article;
import com.sbs.basic1.boudedContext.article.service.ArticleService;
import com.sbs.basic1.boudedContext.base.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor // 필드 중에서 final로 선언된 필드에 대해 생성자를 자동으로 생성
public class ArticleController {
  private final ArticleService articleService;

  @GetMapping("/write")
  @ResponseBody
  public RsData write(String title, String content) {
    if(title == null || title.trim().isBlank()) {
      return RsData.of("F-1", "제목을 입력해주세요.");
    }

    if(content == null || content.trim().isBlank()) {
      return RsData.of("F-2", "내용을 입력해주세요.");
    }
    
    Article createArticle = articleService.save(title, content);

    return RsData.of("S-1", "1번 글이 생성되었습니다.", createArticle);
  }
}
