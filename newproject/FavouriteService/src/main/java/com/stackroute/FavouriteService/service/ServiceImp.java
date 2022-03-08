package com.stackroute.FavouriteService.service;

import com.stackroute.FavouriteService.controller.FavouriteController;
import com.stackroute.FavouriteService.exception.CityAlreadyExistsException;
import com.stackroute.FavouriteService.model.Favourite;
import com.stackroute.FavouriteService.repository.FavouriteRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/* This is ServiceImplementation Class which implements ServiceDao Interface and override all the implemented methods.
 *  Handles exceptions */
@Service
public class ServiceImp implements ServiceDao{
    private FavouriteRepo favouriteRepo;
    private static final Logger logger = (Logger) LoggerFactory.getLogger(FavouriteController.class);
    @Autowired
    public ServiceImp(FavouriteRepo favouriteRepo) {
        this.favouriteRepo = favouriteRepo;
    }

    @Override
    public Favourite addCitytoFav(Favourite favourite) throws CityAlreadyExistsException {

        if(favouriteRepo.existsById(favourite.getCityId())){
            throw  new CityAlreadyExistsException();
        }
        Favourite favourite1 = favouriteRepo.save(favourite);
        return favouriteRepo.save(favourite1);
    }

    @Override
    public List<Favourite> getAllFavCity() {

        return (List<Favourite>) favouriteRepo.findAll();
    }

    @Override
    public void deleteFromFav(int cityId) {
        favouriteRepo.deleteById( cityId );
    }

    @Override
    public Favourite updateFavourite(Favourite favourite) {
        return favouriteRepo.save( favourite );
    }

    @Override
    public List<Favourite> findByUsername(String username) {
        return (List<Favourite>) favouriteRepo.findByUsername( username );
    }


}
