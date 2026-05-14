package com.whattowear.backend.service;

import com.whattowear.backend.domain.WeatherData;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    // 기상청 API 연동 전에 화면 테스트를 위해 임시 날씨 데이터를 반환하는 메서드
    public WeatherData getCurrentWeather(String location) {
        WeatherData dummyData = new WeatherData();

        // 일단 화면에 띄울 임시 데이터 (나중에 진짜 API로 교체할 부분)
        dummyData.setCurrentTemp(22.5);
        dummyData.setMaxTemp(26.0);
        dummyData.setMinTemp(18.0);
        dummyData.setSkyStatus("맑음");
        dummyData.setPop(0); // 강수확률

        return dummyData;
    }
}
