package com.rohan.BlogApp.controllers;

import com.rohan.BlogApp.payload.UserDto;
import com.rohan.BlogApp.services.UserService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserControllers {

        @Autowired
        private UserService userService;

      // Post - create user
        @PostMapping("/")
        public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
            UserDto createUserDto = this.userService.createUser(userDto);
            return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);// status code 201 - created
        }
      // put - update user
        @PutMapping("/{userId}")
        public ResponseEntity<UserDto>updateUser(@Valid @RequestBody UserDto userDto,
                                                 @PathVariable("userId") Integer uid){
            UserDto updatedUser = this.userService.updateUser(userDto,uid);
            return ResponseEntity.ok(updatedUser);// status code 200 - ok
        }
      // delete - delete user
        @DeleteMapping("/{userId}")
        public ResponseEntity<?>deleteUser(@PathVariable("userId")Integer uid){
            this.userService.deleteUser(uid);
            return ResponseEntity.ok(Map.of("message","User deleted successfully"));
        }
     // get - get user
        @GetMapping("/{userId}")
        public ResponseEntity<UserDto>getUser(@PathVariable("userId")Integer uid){
            return ResponseEntity.ok(this.userService.getUserById(uid));
        }
        @GetMapping("/")
        public ResponseEntity<List<UserDto>>getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }
}
