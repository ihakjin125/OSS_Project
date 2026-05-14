package com.whattowear.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
// ▼ Repository와 List를 사용하기 위해 추가된 두 줄
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Entity
@Getter @Setter
public class Clothes {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clothes_id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Thickness thickness;

    @Enumerated(EnumType.STRING)
    private Tpo tpo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}

// 파일 하나에 Enum까지 전부 몰아넣기
enum Category {
    TOP, BOTTOM, OUTER
}

enum Thickness {
    THIN, NORMAL, THICK
}

enum Tpo {
    WORKOUT, DAILY, FORMAL
}

// ▼ 바로 여기에 Repository 인터페이스를 합쳤습니다!
interface ClothesRepository extends JpaRepository<Clothes, Long> {
    // 특정 회원의 옷장 목록만 가져오는 기능
    List<Clothes> findByMember(Member member);
}