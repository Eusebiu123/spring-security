package com.spring.api.controllers;

import com.spring.api.models.User;
import com.spring.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/newUser")
    User newUser(@RequestBody User newUser)
    {
        return userService.registerUser(newUser);
    }
    @GetMapping("/getUsers")
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return userService.updateUser(newUser,id);
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }
}
