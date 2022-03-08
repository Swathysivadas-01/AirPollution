package com.stackroute.FavouriteService.service;

import com.stackroute.FavouriteService.exception.CityAlreadyExistsException;
import com.stackroute.FavouriteService.model.Favourite;

import java.util.List;

public interface ServiceDao {
    /**
     * AbstractMethod to add a city
     */
    Favourite addCitytoFav(Favourite favourite) throws CityAlreadyExistsException;
    /**
     * AbstractMethod to get all cities
     */
    List<Favourite> getAllFavCity();
    /**
     * AbstractMethod to delete city by id
     */
    void deleteFromFav(int cityId);
    /**
     * AbstractMethod to update a city
     */
    Favourite updateFavourite(Favourite favourite);
    /**
     * AbstractMethod to get favourite cities of a user
     */
    List<Favourite> findByUsername(String username);


}
