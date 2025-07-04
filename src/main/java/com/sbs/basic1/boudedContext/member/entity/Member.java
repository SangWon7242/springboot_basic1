package com.sbs.basic1.boudedContext.member.entity;

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

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EntityListeners(AuditingEntityListener.class) // Auditing 기능 활성화
public class Member {
  @Id
  @GeneratedValue(strategy = IDENTITY) // AUTO-INCREMENT
  private Long id;

  @CreatedDate // 데이터 생성 날짜를 자동으로 기록
  @Column(updatable = false) // 생성 시에만 값을 설정하고 수정 시에는 변경되지 않도록 설정
  private LocalDateTime createDate;

  @LastModifiedDate
  private LocalDateTime modifiedDate;

  //  nullable = false : // 해당 필드는 null이 될 수 없음
  @Column(unique = true, nullable = false) // username은 유일하고 null이 될 수 없음
  private String username;
  private String password;
}
