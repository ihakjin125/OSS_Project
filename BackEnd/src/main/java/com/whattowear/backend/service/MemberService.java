package com.whattowear.backend.service;

import com.whattowear.backend.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final EntityManager em;

    public Long join(Member member){
        em.persist(member);
        return member.getId();
    }

    public Member login(String loginId, String password){
        List<Member> members = em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                .setParameter("loginId", loginId)
                .getResultList();

        if(members.isEmpty()){
            return null;
        }

        Member member = members.get(0);
        if(member.getPassword().equals(password)){
            return member;
        }

        return null;
    }
}
