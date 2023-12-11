package com.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.gson.Gson;

@EnableWebMvc
@SpringBootApplication

public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
	
	@Bean
	public Gson gson() {
		return new Gson();
	}

}
