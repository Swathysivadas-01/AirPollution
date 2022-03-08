package com.stackroute.airwatcherservice.repository;

import com.stackroute.airwatcherservice.model.AirWatcher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirWatcherRepository extends MongoRepository<AirWatcher,Integer> {

    //abstract method to get all state of the specified country
    @Query(value = "{country:?0}",fields = "{state:1}")
    public List<AirWatcher> findStateByCountry(String country);
    //abstract method to get all cities of the specified state
    @Query(value = "{state:?0},",fields = "{city:1}")
    public List<AirWatcher> findCitiesByState(String state);
    //abstract method to get airQuality of the specified city
    @Query(value = "{city:?0}")
    public List<AirWatcher> getAllAirWatcher(String city);

}
