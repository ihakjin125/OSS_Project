package com.whattowear.backend.controller;

import com.whattowear.backend.domain.Member;
import com.whattowear.backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<?> login(@RequestParam String loginId, @RequestParam String password) {
        // 기존의 로그인 검증 로직 (아이디, 비밀번호 확인)
        Member member = memberService.login(loginId, password);

        if (member == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패: 아이디 또는 비밀번호를 확인하세요.");
        }

        // 로그인 성공 시, 여러 데이터를 Map에 담아 JSON 형태로 반환합니다.
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("message", member.getNickname() + "님 환영합니다!");
        responseData.put("nickname", member.getNickname());
        responseData.put("constitution", member.getConstitutionWeight()); // 유저의 체질 가중치 값 (-2 ~ 2)

        return ResponseEntity.ok(responseData);
    }
}