package com.blog.controller;

import com.blog.entities.User;
import com.blog.payloads.APIResponse;
import com.blog.payloads.UserDto;
import com.blog.repository.UserRepo;
import com.blog.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // POST - CreateUser()
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto createUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    // PUT - UpdateUser();
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("userId") Integer uid, @Valid @RequestBody UserDto userDto) {
        UserDto updatedUser = this.userService.updateUser(userDto,uid);
        return ResponseEntity.ok(updatedUser);
    }

    // DELETE - deleteUser
    @DeleteMapping("/{userId}")
    public ResponseEntity<APIResponse> deleteUser(@PathVariable("userId") Integer userId){
        this.userService.deleteUser(userId);
        return new ResponseEntity<APIResponse>(new APIResponse("User Deleted Successfully",true), HttpStatus.OK);
    }

    // GET - getUser
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser() {
        return new ResponseEntity<>(this.userService.getAllUsers(),HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId) {
        return new ResponseEntity<>(this.userService.getUserById(userId),HttpStatus.OK);
    }
}
