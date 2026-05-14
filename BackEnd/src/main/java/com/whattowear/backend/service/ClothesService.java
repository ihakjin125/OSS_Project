package com.whattowear.backend.service;

import com.whattowear.backend.domain.Clothes;
import com.whattowear.backend.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClothesService {
    private final EntityManager em;

    public Long addClothes(Clothes clothes){
        em.persist(clothes);
        return clothes.getId();
    }

    public List<Clothes> getClothesByMember(Member member){
        return em.createQuery("select c from Clothes c where c.member = :member", Clothes.class)
                .setParameter("member", member)
                .getResultList();
    }
}
