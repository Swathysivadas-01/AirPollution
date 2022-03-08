package com.stackroute.airwatcherservice.controller;

import com.stackroute.airwatcherservice.model.AirWatcher;
import com.stackroute.airwatcherservice.service.AirWatcherDAO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

//request handler
@RestController
//map to path /api/v1
@RequestMapping("/api/v1")
//used to define all argument constructor
@AllArgsConstructor
//used to work in all origin
//@CrossOrigin(value = "*")

public class AirWatcherController {
    @Autowired
    private AirWatcherDAO service;

    //service to post data
    @PostMapping("/airwatcher")
    public ResponseEntity<AirWatcher> saveAirWatcher(@RequestBody AirWatcher airWatcher){
        return new ResponseEntity<AirWatcher>(service.addAirWatcher(airWatcher), HttpStatus.OK);
    }
    //service to update data
    @PutMapping("/{city}/airwatcher")
    public ResponseEntity<AirWatcher> updateAirWatcher(@PathVariable String city,@RequestBody AirWatcher airWatcher){

        return new ResponseEntity<AirWatcher>(service.updateAirWatcher(city, airWatcher),HttpStatus.OK);
    }
    //service to get state
    @GetMapping("/air/{country}")
    public ResponseEntity<List<AirWatcher>> getStates(@PathVariable String country){
        //logger.info("this is info msg");


        return new ResponseEntity<>(service.getStates(country),HttpStatus.OK);
    }
    //service to get city
    @GetMapping("/{country}/{state}")
    public ResponseEntity<List<AirWatcher>> getCities(@PathVariable String state){
        return new ResponseEntity<>(service.getCities(state),HttpStatus.OK);
    }
    //service to get airQuality details
    @GetMapping("/{city}")
    public List<AirWatcher> getAirQualityDetails(@PathVariable String city){
        return service.getAllAirWatcher(city);
    }

}
