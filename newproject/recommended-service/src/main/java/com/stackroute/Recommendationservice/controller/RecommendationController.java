package com.stackroute.Recommendationservice.controller;

import com.stackroute.Recommendationservice.model.Recommendation;
import com.stackroute.Recommendationservice.service.RecommendationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//request handler.
@RestController
//map to path /api/v1
@RequestMapping("/api/v1")
//used to work in all origin
@CrossOrigin(value = "*")

public class RecommendationController {
    @Autowired
    //Autowire the recommendation service
    private RecommendationService service;

    @GetMapping("/recommendations")
    public ResponseEntity<?> getAllRecommendation() {
        try {
            return new ResponseEntity<List<Recommendation>>(service.getAllRecommendation(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Request Not accepted", HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/recommendation")
    public ResponseEntity<String> saveToRecommendation(@RequestBody Recommendation recommendation) {
        try {
            if (service.saveToRecommendation(recommendation)) {
                return new ResponseEntity<String>("Successfully Added", HttpStatus.CREATED);
            } else
                return new ResponseEntity<String>("Recommendation Not Added ", HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<String>("Error", HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/recommendation/{id}")
    public ResponseEntity<String> deleteRecommendation(@PathVariable int id) {
        try {
            if (service.deleteRecommendation(id)) {
                return new ResponseEntity<String>("Successfully Deleted", HttpStatus.OK);
            } else
                return new ResponseEntity<String>("Recommendation Not Deleted", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<String>("Error", HttpStatus.NOT_FOUND);

        }
    }
}
