package com.a3logics.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;

@Component
public class DBAuthenticationProvider extends DaoAuthenticationProvider {

    public DBAuthenticationProvider() {
        super();

        this.setPasswordEncoder(new BCryptPasswordEncoder());
        this.setUserDetailsService(new UserService());
    }
}
