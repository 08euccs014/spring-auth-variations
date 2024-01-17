package com.a3logics.demo.auth;

import com.a3logics.demo.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Configuration
public class UserService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //UserDetails user = new User("mohit", "{noop}password");

        System.out.println("--------------");
        logger.info("--------i am here in user service {}", username);



        com.a3logics.demo.model.User user = repository.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("Bad credentials");
        }

        return new User(user);
    }
}
