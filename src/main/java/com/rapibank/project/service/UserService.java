package com.rapibank.project.service;

import com.rapibank.project.model.User;
import com.rapibank.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser (User user) {
        // Password encryption logic here
        return userRepository.save(user);
    }

//    public User login(String email, String password) {
//        // Authentication logic here
//    }
}
