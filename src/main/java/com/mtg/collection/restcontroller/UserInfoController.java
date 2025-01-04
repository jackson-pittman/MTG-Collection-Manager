package com.mtg.collection.restcontroller;

import com.mtg.collection.entity.UserInfo;
import com.mtg.collection.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void saveUser(@RequestBody UserInfo userInfo)
    {
        userInfoService.saveUser(userInfo);
    }
}
