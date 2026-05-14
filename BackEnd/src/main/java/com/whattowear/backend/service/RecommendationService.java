package com.whattowear.backend.service;

import com.whattowear.backend.domain.Clothes;
import com.whattowear.backend.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecommendationService {
    private final EntityManager em;

    public List<Clothes> recommend(Member member, Double currentTemp){
        // 1. 유저의 체질 가중치를 반영하여 '실제 체감 온도' 계산
        Double perceivedTemp = currentTemp + member.getConstitutionWeight();

        // 2. 체감 온도(perceivedTemp)에 맞춰서 옷을 필터링하는 로직 (프로토타입용 뼈대)
        // 일단 DB 오류 방지를 위해 해당 유저의 전체 옷을 가져오도록 구성해두었습니다.
        // 나중에 여기에 if(perceivedTemp > 25) 등 상세 필터링을 추가할 예정입니다.
        return em.createQuery("select c from Clothes c where c.member = :member", Clothes.class)
                .setParameter("member", member)
                .getResultList();
    }
}
