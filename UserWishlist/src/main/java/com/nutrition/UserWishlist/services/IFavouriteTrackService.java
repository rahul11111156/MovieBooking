package com.nutrition.UserWishlist.services;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.nutrition.UserWishlist.data.AddFavouriteRequest;
import com.nutrition.UserWishlist.data.FavouriteFoodDetails;
import com.nutrition.UserWishlist.data.RemoveFavouriteRequest;
import com.nutrition.UserWishlist.exception.FoodAlreadyExistsException;
import com.nutrition.UserWishlist.exception.NoFoodFoundException;




@Validated
public interface IFavouriteTrackService {

    FavouriteFoodDetails addToFavourite(AddFavouriteRequest requestData) throws FoodAlreadyExistsException;
  
    List<FavouriteFoodDetails> listFavouriteTracksByUserName(String username) throws NoFoodFoundException;

    FavouriteFoodDetails deleteFavourite(String username,String fdcId) throws NoFoodFoundException;

    FavouriteFoodDetails removeFavourite(RemoveFavouriteRequest requestData) throws NoFoodFoundException;


}
