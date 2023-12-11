package com.nutrition.UserWishlist.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nutrition.UserWishlist.data.AddFavouriteRequest;
import com.nutrition.UserWishlist.data.FavouriteFoodDetails;
import com.nutrition.UserWishlist.data.RemoveFavouriteRequest;
import com.nutrition.UserWishlist.entity.FavoritedItem;
import com.nutrition.UserWishlist.exception.FoodAlreadyExistsException;
import com.nutrition.UserWishlist.exception.NoFoodFoundException;
import com.nutrition.UserWishlist.repository.IFavouriteTrackRepository;
import com.nutrition.UserWishlist.util.FavouriteTrackUtil;





@Service
public class FavouriteTrackServiceImpl implements IFavouriteTrackService {
    @Autowired
    private IFavouriteTrackRepository repository;

    @Autowired
    private FavouriteTrackUtil util;


    @Override
    public FavouriteFoodDetails addToFavourite(AddFavouriteRequest requestData) throws FoodAlreadyExistsException {
        Optional<FavoritedItem> optional = repository.findByUsernameAndFdcId(requestData.getUsername(),requestData.getFdcId());
        if (optional.isPresent()) {
            throw new FoodAlreadyExistsException("Food is already in the favourite list");
        }
        FavoritedItem item = util.toFavouriteTrack(requestData);
        item=repository.save(item);
        FavouriteFoodDetails details = util.toFavouriteTrackDetails(item);
        return details;
    }

    /* Instead of removeFavourite(RemoveFavouriteRequest requestData). We are using  deleteFavourite(String username,String fdcId) for removing favourite food items.*/
    @Override
    public FavouriteFoodDetails removeFavourite(RemoveFavouriteRequest requestData) throws NoFoodFoundException {
        Optional<FavoritedItem> optional = repository.findByUsernameAndFdcId(requestData.getUsername(),requestData.getFdcId());
        if (!optional.isPresent()) {
            throw new NoFoodFoundException("No Food found");
        }
        FavoritedItem item = optional.get();
        repository.delete(item);
        FavouriteFoodDetails details = util.toFavouriteTrackDetails(item);
        return details;
    }

    @Override
    public List<FavouriteFoodDetails> listFavouriteTracksByUserName(String username) throws NoFoodFoundException {
        List<FavoritedItem> items = repository.findByUsername(username);
        if (items.isEmpty()) {
            throw new NoFoodFoundException("No Food found");
        }
        List<FavouriteFoodDetails> desired = util.toFavouriteTrackDetails(items);
        return desired;
    }

	@Override
	public FavouriteFoodDetails deleteFavourite(String username,String fdcId) throws NoFoodFoundException {
		Optional<FavoritedItem> optional = repository.findByUsernameAndFdcId(username, fdcId);
		if(!optional.isPresent()) {
			throw new NoFoodFoundException("No Food found");
		}
		FavoritedItem item = optional.get();
		repository.delete(item);
		FavouriteFoodDetails details = util.toFavouriteTrackDetails(item);
		return details;
	}

	
}
