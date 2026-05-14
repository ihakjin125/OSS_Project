package com.whattowear.backend.domain;

import lombok.Getter;
import lombok.Setter;

// 이 클래스는 DB에 저장되는 @Entity가 아니라, 데이터를 전달하는 DTO 역할만 합니다.
@Getter @Setter
public class WeatherData {
    private Double currentTemp; // 현재 기온
    private Double maxTemp;     // 최고 기온
    private Double minTemp;     // 최저 기온
    private String skyStatus;   // 하늘 상태 (맑음, 구름많음 등)
    private Integer pop;        // 강수 확률 (%)
}