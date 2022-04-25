package com.rateyourmedia.rym_service;

import com.rateyourmedia.rym_entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
    public User registerUser(User user);

    public User getUserByUsername(String username);

}
