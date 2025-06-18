package org.example.springtest.weather.controller;


import lombok.extern.slf4j.Slf4j;
import org.example.springtest.weather.dto.ForecastDto;
import org.example.springtest.weather.dto.WeatherDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@Slf4j
@RequestMapping("/weather")
@PropertySource({"classpath:/application.properties"})
@CrossOrigin(origins = "*") // CORS 설정
public class WeatherController {
    @Value("${weather.url}")
    private String weatherUrl;

    @Value("${weather.api_key}")
    private String apiKey;

    @GetMapping("/{city}")
    public WeatherDto getCurrentWeather(@PathVariable("city") String city) {
        log.info("도시 날씨 요청: {}", city);

        RestTemplate restTemplate = new RestTemplate();

        String url = UriComponentsBuilder.fromHttpUrl(weatherUrl)
                .queryParam("q", city)
                .queryParam("units", "metric")
                .queryParam("lang", "kr")
                .queryParam("APPID", apiKey)
                .toUriString();

        return restTemplate.getForObject(url, WeatherDto.class);
    }

    @GetMapping("/forecast/{city}")
    @ResponseBody
    public ForecastDto forecast(@PathVariable String city) {
        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder
                .fromHttpUrl("https://api.openweathermap.org/data/2.5/forecast")
                .queryParam("q", city)
                .queryParam("units", "metric")
                .queryParam("lang", "kr")
                .queryParam("APPID", apiKey)
                .toUriString();

        return restTemplate.getForObject(url, ForecastDto.class);
    }
}
