package com.whattowear.backend.controller;

import com.whattowear.backend.domain.Clothes;
import com.whattowear.backend.domain.Member;
import com.whattowear.backend.domain.WeatherData;
import com.whattowear.backend.service.ClothesService;
import com.whattowear.backend.service.RecommendationService;
import com.whattowear.backend.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/main")
@RequiredArgsConstructor
public class MainController {

    private final RecommendationService recommendationService;
    private final WeatherService weatherService;
    private final ClothesService clothesService;

    // 1. 메인 화면 날씨 정보 가져오기 API
    @GetMapping("/weather")
    public WeatherData getWeather(@RequestParam String location) {
        // 날씨 서비스에서 가짜(더미) 데이터를 가져와서 프론트로 넘겨줌
        return weatherService.getCurrentWeather(location);
    }

    // 2. 메인 화면 옷 추천 API
    @PostMapping("/recommend")
    public List<Clothes> getRecommendation(@RequestBody Member member, @RequestParam Double currentTemp) {
        // 추천 서비스 알고리즘을 돌려서 결과를 프론트로 넘겨줌
        return recommendationService.recommend(member, currentTemp);
    }

    // 3. 내 옷장 목록 가져오기 API
    @PostMapping("/wardrobe")
    public List<Clothes> getMyWardrobe(@RequestBody Member member) {
        return clothesService.getClothesByMember(member);
    }
}