package com.rateyourmedia.rym_service.impl;

import amin.aoulkadi.mychat.dtos.AccountDTO;
import com.rateyourmedia.rym_apiClient.PublicExternalUserServiceIF;
import com.rateyourmedia.rym_entity.User;
import com.rateyourmedia.rym_repository.UserRepository;
import com.rateyourmedia.rym_service.UserService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userrepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private PublicExternalUserServiceIF publicExternalUserServiceIF;
    //MyChat ApiKey
    @Value("#{environment.apiKey}")
    private String apiKey;

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public User registerUser(User newUser) {
        //Registrierung bei Partner MyChat
        AccountDTO accountDTO= publicExternalUserServiceIF.registerExternalUser(apiKey,newUser.getUsername());
        if(accountDTO != null){
            newUser.setChatID(accountDTO.getAccountId());
            newUser.setChat_registered(true);
        }
        else newUser.setChat_registered(false);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userrepo.save(newUser);
    }

    @Override
    public User getUserByUsername(String username) {
        return userrepo.findById(username).orElseThrow(
                () -> new ServiceException("User with " + username + " not found.")
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userrepo.findById(username).orElseThrow(
                () -> new UsernameNotFoundException("User with " + username + " not found."));
    }
}
