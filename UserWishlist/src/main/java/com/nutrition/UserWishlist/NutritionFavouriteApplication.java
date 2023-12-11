package com.nutrition.UserWishlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Configuration
@EnableDiscoveryClient
public class NutritionFavouriteApplication {

	public static void main(String[] args) {
		SpringApplication.run(NutritionFavouriteApplication.class, args);
	}
	
	@Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
