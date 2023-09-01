package com.spring.api.services;


import com.spring.api.exception.UserNotFoundException;
import com.spring.api.models.User;
import com.spring.api.repository.UseriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UseriService {
    @Autowired
    private UseriRepository userRepository;
    public User registerUser (User newUser){
        return userRepository.save(newUser);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id)
    {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
    public User updateUser(User newUser, Long id)
    {
        return userRepository.findById(id)
                .map(user -> {
                    user.setAge(newUser.getAge());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    public String deleteUser(Long id)
    {
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return  "User with id "+id+" has been deleted success.";
    }
}
