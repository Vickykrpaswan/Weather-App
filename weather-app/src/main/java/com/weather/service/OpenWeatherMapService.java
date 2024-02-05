package com.weather.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.weather.model.WeatherData;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;

@Service
public class OpenWeatherMapService implements WeatherService{

    //Api Key
    private String apiKey="1d0d60d485cf90175e6beb1cf77d8394";

    @Override
    public WeatherData getWeatherData(String cityName) throws IOException {

        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + apiKey;

        // Api Integration
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Reading the data from network
        InputStream inputStream = connection.getInputStream();
        InputStreamReader reader = new InputStreamReader(inputStream);
        //System.out.println(reader);

        // want to store in string
        StringBuilder responseContent = new StringBuilder();

        // get input from reader, we create scanner object
        Scanner scanner = new Scanner(reader);

        while (scanner.hasNext()) {
            responseContent.append(scanner.nextLine());
        }
        //System.out.println(responseContent);
        scanner.close();

        // Type casting= parsing the data into JSON
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(responseContent.toString(),JsonObject.class);
        //System.out.println(jsonObject);

        if (jsonObject != null) {
            WeatherData weatherData = new WeatherData();
            // city
            String name= jsonObject.get("name").getAsString();
            weatherData.setCityName(name);
            //System.out.println(weatherData.getCityName());

            // Date & Time
            long dateTimestamp = jsonObject.get("dt").getAsLong()*1000;
            String date = new Date(dateTimestamp).toString();
            weatherData.setDate(date);
            //System.out.println(date);

            // Temperature
            double temperatureKelvin = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
            int temperatureCelsius = (int) (temperatureKelvin - 273.15);
            weatherData.setTemperature(temperatureCelsius);

            // Humidity
            int humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsInt();
            weatherData.setHumidity(humidity);

            // Wind Speed
            double windSpeed = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble();
            weatherData.setWindSpeed(windSpeed);

            // Weather Condition
            String weatherCondition = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main")
                    .getAsString();
            weatherData.setWeather(weatherCondition.toString());
            //System.out.println(weatherData.getWeather());

            connection.disconnect();

            return weatherData;
        }else {
            return null;
        }
    }



    

    // Inner class to represent the "main" section of the API response

    public static class WeatherCondition{
        private String main;
        private String description;

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public static class Wind{
        private double speed;

        public double getSpeed() {
            return speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }
    }
}
