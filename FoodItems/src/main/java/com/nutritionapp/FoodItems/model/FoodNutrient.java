package com.nutritionapp.FoodItems.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodNutrient {
	private int number;
    private String name;
    private double amount;
    private String unitName;
    private String derivationCode;
    private String derivationDescription;

}
