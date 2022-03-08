package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.exception.UserAlreadyExistException;
import com.stackroute.exception.UserNotFoundException;
//
public interface UserService {

    //abstract method for save user
    User saveUser(User user) throws UserAlreadyExistException;

    //abstract method for find user by id and password
    User findByIdAndPassword(String id, String password) throws UserNotFoundException;
}
