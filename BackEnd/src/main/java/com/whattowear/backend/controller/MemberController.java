package com.whattowear.backend.controller;

import com.whattowear.backend.domain.Member;
import com.whattowear.backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController // 프론트엔드(HTML/JS)와 데이터를 주고받기 위한 컨트롤러
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 1. 회원가입 API
    @PostMapping("/signup")
    public String signup(@RequestBody Member member) {
        memberService.join(member);
        return "회원가입이 완료되었습니다!"; // 프론트엔드로 보낼 메시지
    }

    // 2. 로그인 API (간단한 버전)
    @PostMapping("/login")
    public String login(@RequestParam String loginId, @RequestParam String password) {
        Member loginMember = memberService.login(loginId, password);
        if (loginMember == null) {
            return "로그인 실패: 아이디나 비밀번호를 확인해주세요.";
        }
        return loginMember.getNickname() + "님 환영합니다!";
    }
}