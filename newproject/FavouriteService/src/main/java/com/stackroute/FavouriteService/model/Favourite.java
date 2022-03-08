package com.stackroute.FavouriteService.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

//Default constructor
@NoArgsConstructor
//Parameterised Constructor
@AllArgsConstructor
//Getters and Setters
@Data
@Document(collection = "Favourite_Record")


public class Favourite {
//@Id annotation makes cityId as primary key
    @Id
    private int cityId;
    private String username;
    private String cityName;

}
