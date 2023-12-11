package com.nutritionapp.FoodItems.services;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.nutritionapp.FoodItems.exception.FoodNotFoundException;


@Service
public class ApiServiceImpl implements ApiService {

    @Value("${api.url}")
    private String apiUrl;

    @Value("${api.key}")
    private String apiKey;

    private final Logger logger = LoggerFactory.getLogger(ApiServiceImpl.class);
    private final RestTemplate restTemplate;

    @Autowired
    public ApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Object> getFoods() {
        String url = apiUrl + "/foods/list?api_key=" + apiKey;
        ResponseEntity<Object> responseEntity =restTemplate.getForEntity(url, Object.class);
        return Arrays.asList(responseEntity.getBody());
    }

    @Override
    public List<Object> getFoodByFdcId(int fdcId) {
        String url = apiUrl + "/food/" + fdcId + "?api_key=" + apiKey;
        try {
            return Collections.singletonList(restTemplate.getForObject(url, Object.class));
        } catch (HttpClientErrorException.NotFound ex) {
            logger.error("Food not found for FDC ID: {}", fdcId);
            throw new FoodNotFoundException("Food not found for FDC ID: " + fdcId);
        }
    }
    
    @Override
    public List<Object> getFoodSearchApi(String query) {
        try {
            String url = apiUrl + "/foods/search/" + query + "?api_key=" + apiKey;
            System.out.println(apiUrl);
            ResponseEntity<Object> responseEntity =restTemplate.getForEntity(url, Object.class);
            return Arrays.asList(responseEntity.getBody());
        } catch (Exception e) {
            logger.error("An error occurred while searching for food with keyword: {}", query, e);
            throw new RuntimeException("Error searching for food", e);
        }
    }
}