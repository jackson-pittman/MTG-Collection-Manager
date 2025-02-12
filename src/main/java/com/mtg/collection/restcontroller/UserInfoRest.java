package com.mtg.collection.restcontroller;

import com.mtg.collection.entity.AuthRequest;
import com.mtg.collection.entity.UserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequestMapping(path = "api/user")
public interface UserInfoRest
{
    /*
    @GetMapping("/")
    ResponseEntity<List<UserInfo>> getAllUsers();
    */

    @PostMapping(path = "/addUser")
    ResponseEntity<?> addNewUser(@RequestBody UserInfo userInfo);

    @PostMapping(path = "/login")
    ResponseEntity<?> login(@RequestBody AuthRequest authRequest);
    /*
    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteStudent(@PathVariable Long id);
    */

    @PostMapping("/updateUserStatus")
    ResponseEntity<?> updateUser(@RequestBody UserInfo userInfo);


    @GetMapping(path = "/getAllUsers")
    ResponseEntity<?> getAllUsers();

    @GetMapping("/checkToken")
    ResponseEntity<?> checkToken();


}
