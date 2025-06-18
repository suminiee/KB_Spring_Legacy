package org.example.springtest.weather.dto;

import lombok.Data;

import java.util.List;

@Data
public class ForecastItem {
    private long dt;
    private Main main;
    private List<WeatherItem> weather;
}