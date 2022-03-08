package com.stackroute.airwatcherservice.service;

import com.stackroute.airwatcherservice.controller.AirWatcherController;
import com.stackroute.airwatcherservice.model.AirWatcher;
import com.stackroute.airwatcherservice.repository.AirWatcherRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

//Service class of airWatcher
@Service
@AllArgsConstructor
public class AirWatcherServiceImpl implements AirWatcherDAO{
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    private AirWatcherRepository repository;
    private static final Logger logger = (Logger) LoggerFactory.getLogger(AirWatcherController.class);
    //Override all methods from AirWatcherDao
    @Override
    public AirWatcher addAirWatcher(AirWatcher airWatcher) {
        return repository.save(airWatcher);
    }

    @Override
    public AirWatcher updateAirWatcher(String city, AirWatcher airWatcher) {
        return repository.save(airWatcher);
    }

    @Override
    public List<AirWatcher> getStates(String country) {
        Criteria criteria = new Criteria("country").is(country);
        Query query = new Query();
        query.addCriteria(criteria);
        return mongoTemplate.findDistinct(query,"state",AirWatcher.class,AirWatcher.class);
    }

    @Override
    public List<AirWatcher> getCities(String state) {
        return repository.findCitiesByState(state);
    }

    @Override
    public List<AirWatcher> getAllAirWatcher(String city) {
        return (List<AirWatcher>) repository.getAllAirWatcher(city);
    }
}
