package com.gmail.goyter012.campus.service;

import com.gmail.goyter012.campus.model.User;
import com.gmail.goyter012.campus.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {


    @Autowired
    private UserRepo userRepo;


    public User findUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public User saveUser(User user) {
        userRepo.save(user);
        return user;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }
}
