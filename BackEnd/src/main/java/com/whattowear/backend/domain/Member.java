package com.whattowear.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
// ▼ 이 두 줄이 추가되었습니다
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member_table")
@Getter @Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String loginId;
    private String password;
    private String nickname;
    private String location;

    private Double constitutionWeight = 0.0;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Clothes> clothesList = new ArrayList<>();
}

// ▼ 바로 여기에 Repository 인터페이스를 합쳤습니다! (클래스 1개로 치기 위한 꼼수입니다)
interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByLoginId(String loginId);
}