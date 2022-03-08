package com.stackroute.controller;

import com.stackroute.config.JWTTokenGenerator;
import com.stackroute.domain.User;
import com.stackroute.exception.UserAlreadyExistException;
import com.stackroute.exception.UserNotFoundException;
import com.stackroute.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RestController annotation is used to create Restful web services using Spring MVC
 */
@RestController
/**
 * RequestMapping annotation maps HTTP requests to handler methods
 */
@RequestMapping("/api/v1/")
public class UserController {

    private UserService userService;
    private JWTTokenGenerator jwtTokenGenerator;
    ResponseEntity<?> responseEntity;

    /**
     * To get the property values
     */
    @Value("${app.controller.exception.message1}")
    private String message1;

    @Value("${app.controller.exception.message2}")
    private String message2;

    @Value("${app.controller.exception.message3}")
    private String message3;



    @Autowired
    public UserController(UserService userService, JWTTokenGenerator jwtTokenGenerator) {
        this.userService = userService;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    //If user registration is successful,then status is "created" otherwise it is conflict
    @PostMapping("user")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        String regex = "^(.+)@(.+)$";
        String regex1 = "^[a-zA-Z]+$";
        String regex2 = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
        String regex3 = "^[0-9]+$";
        String regex4 = "^[a-zA-Z]+$";


        Pattern pattern = Pattern.compile(regex);
        Pattern pattern1 = Pattern.compile(regex1);
        Pattern pattern2 = Pattern.compile(regex2);
        Pattern pattern3 = Pattern.compile(regex3);
        Pattern pattern4 = Pattern.compile(regex4);
        try {
            if (user.getId() == null || user.getPassword() == null || user.getId() == "" ||user.getPassword() == "" || user.getPassword() == null || user.getName() == "" || user.getName() == null || user.getAge() == "" || user.getAge() == null|| user.getCountry() == "" || user.getCountry() == null)  {
                throw new UserNotFoundException(message1);
            }
            Matcher matcher = pattern.matcher(user.getId());
            Matcher matcher1= pattern1.matcher(user.getName());
            Matcher matcher2= pattern2.matcher(user.getPassword());
            Matcher matcher3= pattern3.matcher(user.getAge());
            Matcher matcher4= pattern4.matcher(user.getCountry());
            if(matcher.matches()==false){
                throw new UserNotFoundException(message3);
            }
            if(matcher1.matches()==false){
                throw new UserNotFoundException(message3);
            }
            if(matcher2.matches()==false){
                throw new UserNotFoundException(message3);
            }
            if(matcher3.matches()==false){
                throw new UserNotFoundException(message3);
            }
            if(matcher4.matches()==false){
                throw new UserNotFoundException(message3);
            }
            User savedUser = userService.saveUser(user);
            responseEntity = new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (UserAlreadyExistException | UserNotFoundException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

   //If credentials is valid,token is generated.Otherwise,thrown exception
    @PostMapping("login/user")
    public ResponseEntity<?> loginUser(@RequestBody User user) {


        try {
            if (user.getId() == null || user.getPassword() == null) {
                throw new UserNotFoundException(message1);
            }

            User userDetails = userService.findByIdAndPassword(user.getId(),user.getPassword());

            if (userDetails == null) {
                throw new UserNotFoundException(message2);
            }

            responseEntity = new ResponseEntity<>(jwtTokenGenerator.generateToken(userDetails),HttpStatus.OK);
        } catch (UserNotFoundException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException exc) {
        Map<String, String> errors = new HashMap<>();
        exc.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
