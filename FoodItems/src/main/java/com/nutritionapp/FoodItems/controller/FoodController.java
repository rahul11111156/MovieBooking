package com.nutritionapp.FoodItems.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.nutritionapp.Food.exception.NoFoodFoundException;
import com.nutritionapp.FoodItems.exception.FoodNotFoundException;
import com.nutritionapp.FoodItems.model.Food;
import com.nutritionapp.FoodItems.services.ApiService;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    @Autowired
    private ApiService apiService;

    private final Logger logger = LoggerFactory.getLogger(FoodController.class);

    @GetMapping("/getfoods")
    public ResponseEntity<List<Object>> getFoods() {
        try {
            List<Object> foods = apiService.getFoods();
            return ResponseEntity.ok(foods);
        } catch (Exception e) {
            logger.error("Error fetching foods", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/foodbyfdcid/{fdcId}")
    public ResponseEntity<List<Object>> getFoodByFdcId(@PathVariable int fdcId) {
        try {
            List<Object> food = apiService.getFoodByFdcId(fdcId);
            return ResponseEntity.ok(food);
        } catch (FoodNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error fetching food by FDC ID: {}", fdcId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    

	@GetMapping("/search/{query}")
	public ResponseEntity<List<Object>> getFoodSearchApi(@PathVariable("query") String query){
		
			List<Object> foods = apiService.getFoodSearchApi(query);
            return ResponseEntity.ok(foods);
		}
      
      }
  

