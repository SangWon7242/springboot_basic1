package com.sbs.basic1.boudedContext.article.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity // DB 테이블로 매핑
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class) // INSERT, UPDATE 시점에 Auditing 기능을 활성화
public class Article {
  @Id // Primary Key로 설정
  @GeneratedValue(strategy = IDENTITY) // AUTO-INCREMENT
  private Long id;

  @CreatedDate // 데이터 생성 날짜를 자동으로 기록
  @Column(updatable = false) // 생성 시에만 값을 설정하고 수정 시에는 변경되지 않도록 설정
  private LocalDateTime createDate; // 데이터 생성 날짜

  @LastModifiedDate // 마지막 수정 시점에 날짜를 자동 갱신
  private LocalDateTime modifiedDate; // 데이터 수정 날짜
  private String title;
  private String content;
}
