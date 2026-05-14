package com.whattowear.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

@Entity @Getter @Setter
public class Feedback {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long memberId;
    private Integer score;
    private Double tempAtTime;
    private LocalDateTime createdAt;
}

// 특정 회원의 피드백 목록을 가져오기 위한 한 줄 추가
interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByMemberId(Long memberId);
}