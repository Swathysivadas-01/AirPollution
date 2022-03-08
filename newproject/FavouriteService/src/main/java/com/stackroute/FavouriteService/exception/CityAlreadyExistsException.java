package com.stackroute.FavouriteService.exception;
//Handles Exception if City already exist
public class CityAlreadyExistsException extends Exception{
    private String msg;

    public CityAlreadyExistsException() {
    }

    public CityAlreadyExistsException(String msg, String message) {
        super(message);
        this.msg = msg;
    }

}
