package com.stackroute.airwatcherservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
//collection name as AirQuality_Record
@Document(collection = "AirQuality_Record")
//used to set all argument constructor
@AllArgsConstructor
//used to set no argument constructor
@NoArgsConstructor
//used to set get data
@Getter
//used to set data
@Setter
public class AirWatcher {
    //id should be primary key
    @Id
    private int id;
    //other properties of the model class
    private String country;
    private String state;
    private String city;
    private String pollutionLevel;
    private String airQualityIndex;
    private String mainPollutant;


}
