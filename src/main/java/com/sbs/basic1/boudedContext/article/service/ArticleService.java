package com.sbs.basic1.boudedContext.article.service;

import com.sbs.basic1.boudedContext.article.entity.Article;
import com.sbs.basic1.boudedContext.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
  private final ArticleRepository articleRepository;

  public Article write(String title, String content) {
    Article article = Article.builder()
        .title(title)
        .content(content)
        .build();

    articleRepository.save(article);
    // articleRepository.save() 메서드는 저장된 엔티티를 반환합니다.

    return article;
  }

  public List<Article> findAll() {
    return articleRepository.findAll();
  }

  public List<Article> findAllByOrderByIdDesc() {
    return articleRepository.findAllByOrderByIdDesc();
  }

  public Article findById(Long id) {
    return articleRepository.findById(id)
        .orElse(null); // Optional에서 값을 꺼내고, 값이 없으면 null을 반환
  }
}
