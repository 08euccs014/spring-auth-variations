package com.a3logics.demo;

import com.a3logics.demo.model.User;
import com.a3logics.demo.repositories.UserRepository;
import com.a3logics.demo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class DemoController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("/")
    public ResponseEntity<Object> index() {

        HashMap response = new HashMap();
        response.put("message", "Greetings from Spring Boot!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> doLogin(@RequestParam String username, @RequestParam String password) {

        System.out.println("----------"+ username);

        User user = userRepository.findByUsername(username);

        if(user == null) {
            throw new BadCredentialsException("Username is incorrect");
        }

        boolean isMatch = new BCryptPasswordEncoder().matches(password, user.getPassword());

        if(!isMatch) {
            throw new BadCredentialsException("Password is incorrect");
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getUsername(), user.getId());

        HashMap response = new HashMap();
        response.put("token", token);
        response.put("message", "Greetings from Spring Boot!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}


