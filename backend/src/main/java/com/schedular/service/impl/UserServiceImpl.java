package com.schedular.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.schedular.model.User;
import com.schedular.repository.UserRepository;
import com.schedular.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

  
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void SaveUser(User user) {
        
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword); 

        userRepository.save(user);
    }

    @Override
    public void getSingerUser(Long userId) {
        userRepository.findById(userId);
    }

    @Override
    public User saveGoogleUser(String email, String name) {

        // Check if user already exists
        User existing = userRepository.findByEmail(email).orElse(null);
        if (existing != null) {
            return existing;
        }

        // Create new user
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setUsername(email); 
        newUser.setEnabled(true);

       
        newUser.setPassword(passwordEncoder.encode("google-oauth-user"));

        return userRepository.save(newUser);
    }
    
    // New login validation
    @Override
    public User loginUser(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null; // invalid
    }

    
    @Override
    public User socialLogin(String email, String name) {
        return saveGoogleUser(email, name);
    }



}