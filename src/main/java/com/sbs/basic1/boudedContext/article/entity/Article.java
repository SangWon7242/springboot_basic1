package com.sbs.basic1.boudedContext.article.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity // DB 테이블로 매핑
public class Article {
  @Id
  private Long id;
  private String title;
  private String content;
}
