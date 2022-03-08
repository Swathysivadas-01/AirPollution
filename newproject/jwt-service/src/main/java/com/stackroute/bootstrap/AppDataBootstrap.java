package com.stackroute.bootstrap;

import com.stackroute.domain.User;
import com.stackroute.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AppDataBootstrap implements CommandLineRunner {
    private UserRepository userRepository;

    /**
     * To get the property values
     */
    @Value("${app.seeddata.id1}")
    private String id1;

    @Value("${app.seeddata.id2}")
    private String id2;

    @Value("${app.seeddata.password1}")
    private String password1;

    @Value("${app.seeddata.password2}")
    private String password2;

    @Value("${app.seeddata.name1}")
    private String name1;

    @Value("${app.seeddata.name2}")
    private String name2;

    @Value("${app.seeddata.age1}")
    private String age1;

    @Value("${app.seeddata.age2}")
    private String age2;

    @Value("${app.seeddata.country1}")
    private String country1;

    @Value("${app.seeddata.country2}")
    private String country2;

    @Value("${app.seeddata.logMessage}")
    private String logMessage;


    /**
     * Use constructor based DI to inject UserRepository here
     */
    @Autowired
    public AppDataBootstrap(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User(id1, password1, name1, age1, country1);
        User user2 = new User(id2, password2, name2, age2, country2);
        userRepository.save(user1);
        userRepository.save(user2);
        log.debug(logMessage);
    }
}
