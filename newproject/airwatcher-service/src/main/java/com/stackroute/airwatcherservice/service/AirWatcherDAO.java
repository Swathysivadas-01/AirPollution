package com.stackroute.airwatcherservice.service;

import com.stackroute.airwatcherservice.model.AirWatcher;

import java.util.List;

public interface AirWatcherDAO {
    //abstract method to save air quality details
    AirWatcher addAirWatcher(AirWatcher airWatcher);
    //abstract method to update air quality details of specific city
    public  AirWatcher updateAirWatcher(String city, AirWatcher airWatcher);
    //abstract method to get all states of specific country
    List<AirWatcher> getStates(String country);
    //abstract method to get all cities of specific state
    List<AirWatcher> getCities(String state);
    //abstract method to get air quality details of specific city
    List<AirWatcher> getAllAirWatcher(String city);
}
