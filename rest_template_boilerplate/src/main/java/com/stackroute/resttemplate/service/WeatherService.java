package com.stackroute.resttemplate.service;

import com.stackroute.resttemplate.model.Weather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    //add your api key here
	@Value("${weather.api.key}")
    private static final String API_KEY = "bc72f42180da43ef948174336232711";

    //add the base api url here
	@Value("${weather.api.url}")
    private static final String API_URL = "http://api.weatherapi.com/v1/current.json";

    private final RestTemplate restTemplate;
    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //using rest template, get the weather details of a city
    public Weather getWeather(String city) {
    	String endpoint = API_URL + "?key=bc72f42180da43ef948174336232711&q="+city;
    	Weather weather = restTemplate.getForObject(endpoint, Weather.class);
      return weather;
    }


}
