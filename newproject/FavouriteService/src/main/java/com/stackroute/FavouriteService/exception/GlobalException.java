package com.stackroute.FavouriteService.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
//This class represents the global exception
@ControllerAdvice
public class GlobalException {
    @Value(value = "${data.exception.message1}")
    private String message1;

    @ExceptionHandler(value = CityAlreadyExistsException.class)
    public ResponseEntity<String> cityAlreadyExists(CityAlreadyExistsException exception){
        return new ResponseEntity<String>(message1, HttpStatus.CONFLICT);
    }

}
