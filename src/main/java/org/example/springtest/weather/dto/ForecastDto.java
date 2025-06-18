package org.example.springtest.weather.dto;

import lombok.Data;

import java.util.List;

@Data
public class ForecastDto {
    private City city;
    private List<ForecastItem> list;
}