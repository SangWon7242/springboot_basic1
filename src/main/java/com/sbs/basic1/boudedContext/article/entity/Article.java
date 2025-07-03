package com.sbs.basic1.boudedContext.article.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity // DB 테이블로 매핑
@Builder
@Data
public class Article {
  @Id
  @GeneratedValue(strategy = IDENTITY) // AUTO-INCREMENT
  private Long id;
  private LocalDate createdDate; // 데이터 생성 날짜
  private LocalDate modifiedDate; // 데이터 수정 날짜
  private String title;
  private String content;
}
