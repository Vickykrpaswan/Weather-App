package com.weather.service;

import com.weather.model.WeatherData;

import java.io.IOException;

public interface WeatherService {
    WeatherData getWeatherData(String cityName) throws IOException;
}
