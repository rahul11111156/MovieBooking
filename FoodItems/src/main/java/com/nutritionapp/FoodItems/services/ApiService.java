package com.nutritionapp.FoodItems.services;

import java.util.List;


import com.nutritionapp.FoodItems.model.Food;


public interface ApiService {

	    List<Object> getFoods();
	    List<Object> getFoodByFdcId(int fdcId);
	    public List<Object> getFoodSearchApi(String query);
	    
	}
	


