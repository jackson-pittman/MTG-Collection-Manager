package com.mtg.collection.restcontroller;

import com.mtg.collection.entity.UserInfo;
import com.mtg.collection.service.UserInfoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserInfoController
{
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/")
    public ResponseEntity<List<UserInfo>> getAllUsers()
    {
        List<UserInfo> users = userInfoService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveUser(@RequestBody UserInfo userInfo)
    {
        userInfoService.saveUser(userInfo);
        return new ResponseEntity<>("User saved successfully", HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id)
    {
        try
        {
            userInfoService.deleteUser(id);
            return ResponseEntity.ok("User with ID "+id+" deleted successfully");
        }
        catch (EntityNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<UserInfo> updateUser(@PathVariable Long id, @RequestBody UserInfo userInfo)
    {
        UserInfo updatedUser = userInfoService.updateUser(id, userInfo);
        return ResponseEntity.ok(updatedUser);
    }
    @GetMapping("/{email}")
    public ResponseEntity<UserInfo> findUserByEmail(@PathVariable String email)
    {
        UserInfo foundUser = userInfoService.findUserByEmail(email);
        return ResponseEntity.ok(foundUser);
    }
}
