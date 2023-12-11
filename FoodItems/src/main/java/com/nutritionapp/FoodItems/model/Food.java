package com.nutritionapp.FoodItems.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Food {
	
	private String dataType;
    private String description;
    private int fdcId;
    private List<FoodNutrient> foodNutrients;
    private String publicationDate;
    private String brandOwner;
    private String gtinUpc;
    private int ndbNumber;
    private String foodCode;

}
