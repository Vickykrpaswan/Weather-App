package com.weather.controller;

import com.weather.model.WeatherData;
import com.weather.service.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class HomeController {

    WeatherService weatherService;

    public HomeController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/weather")
    public String getWeather(@RequestParam String cityName, Model model) throws IOException {
        WeatherData weatherData= weatherService.getWeatherData(cityName);
        model.addAttribute("weatherData", weatherData);
        //System.out.println(weatherData.getWeather());

        return "weather";
    }
}
