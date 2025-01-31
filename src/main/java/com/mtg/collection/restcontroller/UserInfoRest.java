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
    /*
    @PutMapping("/update/{id}")
    ResponseEntity<UserInfo> updateUser(@PathVariable Long id, @RequestBody UserInfo userInfo);
    */
    /*
    @GetMapping("/{email}")
    ResponseEntity<UserInfo> findUserByEmail(@PathVariable String email);
    */

}
