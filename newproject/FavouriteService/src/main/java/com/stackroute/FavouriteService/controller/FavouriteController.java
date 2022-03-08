package com.stackroute.FavouriteService.controller;

import com.stackroute.FavouriteService.exception.CityAlreadyExistsException;
import com.stackroute.FavouriteService.model.Favourite;
import com.stackroute.FavouriteService.service.ServiceDao;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/v1")
@Slf4j
public class FavouriteController {
    private ServiceDao serviceDao;


   //private static final Logger logger = (Logger) LoggerFactory.getLogger(FavouriteController.class);

    @Autowired
    public FavouriteController(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }

    /* This method  adds cities and return favourite object  */
    @PostMapping("/favourite")
    public ResponseEntity<Favourite> addCitytoFav(@RequestBody Favourite favourite) throws CityAlreadyExistsException {
            return new ResponseEntity<Favourite>( serviceDao.addCitytoFav( favourite ), HttpStatus.CREATED);
    }
    /* This method fetches all cities and returns the list of all favourite cities */
    @GetMapping("favourites")
    public List<Favourite> getAllFavCity(){
       // logger.info("this is info msg");
//        log.error("This is error msg");
//        log.warn("this is warn msg");
//        log.debug("this is debug msg");
//        log.info("this is info msg");
//        log.trace("this is trace msg");

        return serviceDao.getAllFavCity();
    }
    /* This method deletes a city by its id and returns the deleted favourite object */
    @DeleteMapping("favourite/{cityId}")
    public void deleteFromFav(@PathVariable int cityId){
        serviceDao.deleteFromFav(cityId);
    }
    /* This method updates the city content and returns the updated object */
    @PutMapping("favourite")
    public ResponseEntity<Favourite> updateFavourite(@RequestBody Favourite favourite){
        return new ResponseEntity<Favourite>(serviceDao.updateFavourite(favourite),HttpStatus.OK);
    }
    /* This method returns the city content according to the user */
    @GetMapping("favourite/{username}")
    public List<Favourite> findByUsername(@PathVariable String username){
        return (List<Favourite>) serviceDao.findByUsername( username );
    }

}
