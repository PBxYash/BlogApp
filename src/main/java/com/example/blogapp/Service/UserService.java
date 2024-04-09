package com.example.blogapp.Service;

import com.example.blogapp.Entity.User;
import com.example.blogapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User createuser(User user){
        User save = userRepository.save(user);
        return save;
    }

    public List<User> getAllUsers(){
        List<User> all = userRepository.findAll();
        return all;
    }
}
