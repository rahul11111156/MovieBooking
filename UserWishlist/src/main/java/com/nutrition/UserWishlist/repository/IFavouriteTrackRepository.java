package com.nutrition.UserWishlist.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nutrition.UserWishlist.entity.FavoritedItem;







public interface IFavouriteTrackRepository extends JpaRepository<FavoritedItem, String> {

    List<FavoritedItem> findByUsername(String username);


    Optional<FavoritedItem> findByUsernameAndFdcId(String username, String fdcId);

}

