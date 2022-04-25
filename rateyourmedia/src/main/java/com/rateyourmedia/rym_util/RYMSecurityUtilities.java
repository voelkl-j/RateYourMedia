package com.rateyourmedia.rym_util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

@Configuration
public class RYMSecurityUtilities {

    //@Value("#{application-config.user-password-salt}") Ausapplication_properties
    @Value("#{environment.USER_PASSWORD_SALT}")
    private String salt;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(15, new SecureRandom(salt.getBytes(StandardCharsets.UTF_8)));
    }
}
