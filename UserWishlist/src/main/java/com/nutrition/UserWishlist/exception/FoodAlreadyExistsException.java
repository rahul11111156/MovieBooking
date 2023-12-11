package com.nutrition.UserWishlist.exception;

public class FoodAlreadyExistsException extends Exception{
    public FoodAlreadyExistsException(String message) {
        super(message);
    }
}