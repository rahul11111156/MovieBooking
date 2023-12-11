package com.nutrition.UserWishlist.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nutrition.UserWishlist.data.AddFavouriteRequest;
import com.nutrition.UserWishlist.data.FavouriteFoodDetails;
import com.nutrition.UserWishlist.exception.FoodAlreadyExistsException;
import com.nutrition.UserWishlist.exception.NoFoodFoundException;
import com.nutrition.UserWishlist.services.IFavouriteTrackService;



@RestController
@RequestMapping("/food")
public class FavouriteController {

    @Autowired
    private IFavouriteTrackService service;
    
    @Autowired
	private RestTemplate restTemplate;


    @GetMapping("/{username}")
    public ResponseEntity<List<FavouriteFoodDetails>> findAll(@PathVariable("username") String username ,
    		@RequestHeader(value= "Authorization",required = false) String token) {
        try {
        	if(token == null || !token.startsWith("Bearer ")) {
        		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        	}
        	else {
        		RestTemplate rest = new RestTemplate();
        		String url = "http://localhost:9096/users/validate-token?token={token}";
        	    Map<String, String> params = Collections.singletonMap("token", token);
        	    String result = rest.getForObject(url, String.class, params);
        	    if(result.equals("true")) {
        	    	List<FavouriteFoodDetails> response = service.listFavouriteTracksByUserName(username);
                    return ResponseEntity.ok(response);
        	    }else {
        	    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        	    }
        		
        	}
        } catch (NoFoodFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<FavouriteFoodDetails> add(@RequestBody AddFavouriteRequest requestData,
    		@RequestHeader(value="Authorization",required = false) String token) {
        try {
        	if(token == null || !token.startsWith("Bearer ")) {
        		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        	}
        	else {
        		RestTemplate rest = new RestTemplate();
        		String url = "http://localhost:9096/users/validate-token?token={token}";
        	    Map<String, String> params = Collections.singletonMap("token", token);
        	    String result = rest.getForObject(url, String.class, params);
        	    if(result.equals("true")) {
        	    	FavouriteFoodDetails response = service.addToFavourite(requestData);
                    return ResponseEntity.ok(response);
        	    }else {
        	    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        	    }
        		
        	}
            
        } catch (FoodAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @DeleteMapping("/delete/{username}/{fdcId}")
    public ResponseEntity<FavouriteFoodDetails> delete(@PathVariable("username") String username, @PathVariable String fdcId,
    		@RequestHeader(value="Authorization",required = false) String token) {
        try {
        	if(token == null || !token.startsWith("Bearer ")) {
        		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        	}
        	else {
        		RestTemplate rest = new RestTemplate();
        		String url = "http://localhost:9096/users/validate-token?token={token}";
        	    Map<String, String> params = Collections.singletonMap("token", token);
        	    String result = rest.getForObject(url, String.class, params);
        	    if(result.equals("true")) {
        	    	FavouriteFoodDetails response = service.deleteFavourite(username, fdcId);
                    return ResponseEntity.ok(response);
        	    }else {
        	    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        	    }
        		
        	}
            
        } catch (NoFoodFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
