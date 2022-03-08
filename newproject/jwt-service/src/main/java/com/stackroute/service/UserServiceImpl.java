package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.exception.UserAlreadyExistException;
import com.stackroute.exception.UserNotFoundException;
import com.stackroute.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*
 * This class is implementing the UserService interface. This class is annotated with
 * @Service annotation.
 * @Service indicates annotated class is a service
 * which hold business logic in the Service layer
 *
 * */

@Service
public class UserServiceImpl implements UserService {
    // @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    /**
     * To get the property values
     */
    @Value("${app.service.message1}")
    private String message1;

    @Value("${app.service.message2}")
    private String message2;


    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

//override methods
    @Override
    public User saveUser(User user) throws UserAlreadyExistException {

        Optional<User> userResult = userRepository.findById(user.getId());

        if (userResult.isPresent()) {
            throw new UserAlreadyExistException(message1);
        }

        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setName(user.getName());
        newUser.setAge(user.getAge());
        newUser.setCountry(user.getCountry());

        return userRepository.save(newUser);
    }

    @Override
    public User findByIdAndPassword(String id, String password) throws UserNotFoundException {
        String encodedPassword = passwordEncoder.encode(password);
        User authUser = userRepository.findById(id).get();
        if (!passwordEncoder.matches(password, authUser.getPassword())) {
            throw new UserNotFoundException(message2);
        }
        return authUser;
    }

}