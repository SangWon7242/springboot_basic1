package com.sbs.basic1.boudedContext.article.service;

import com.sbs.basic1.boudedContext.article.entity.Article;
import com.sbs.basic1.boudedContext.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ArticleService {
  private final ArticleRepository articleRepository;

  public Article save(String title, String content) {
    Article article = Article.builder()
        .createDate(LocalDateTime.now())
        .modifiedDate(LocalDateTime.now())
        .title(title)
        .content(content)
        .build();

    articleRepository.save(article);
    // articleRepository.save() 메서드는 저장된 엔티티를 반환합니다.

    return article;
  }
}
