package com.stackroute.FavouriteService.repository;

import com.stackroute.FavouriteService.model.Favourite;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
//@Repository marks the specific class as a Data Access Object
@Repository
public interface FavouriteRepo extends MongoRepository<Favourite,Integer> {
    List<Favourite> findByUsername(String username);


}
