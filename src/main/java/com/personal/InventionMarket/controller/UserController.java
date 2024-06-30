package com.personal.InventionMarket.controller;

import com.personal.InventionMarket.dto.UserDTO;
import com.personal.InventionMarket.model.User;
import com.personal.InventionMarket.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> addUser(@RequestBody UserDTO userDTO){
        log.debug("Inside controller adding user");
        User user = userService.addUser(userDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId){
        log.debug("Inside controller getting user");
        User user = userService.getUserDetails(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        log.debug("Inside controller getting all Users");
        List<User> users = userService.getAllUserDetails();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(users);
    }

    @PostMapping("/updateUser/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable String userId, @RequestBody UserDTO newUserDetailsDTO){
        log.debug("Inside controller updating user");
        User user = userService.updateUserDetails(userId, newUserDetailsDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user);
    }
}
