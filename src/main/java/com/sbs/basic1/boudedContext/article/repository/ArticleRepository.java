package com.sbs.basic1.boudedContext.article.repository;

import com.sbs.basic1.boudedContext.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

// @Repository // 생략 가능
// JpaRepository<Article, Long>
// Article : 엔티티 클래스
// Long : 엔티티 클래스의 ID 타입
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
