package com.mtg.collection.service;

import com.mtg.collection.entity.AuthRequest;
import com.mtg.collection.entity.UserInfo;
import org.springframework.http.ResponseEntity;

public interface UserInfoService
{

    ResponseEntity<?> addNewUser(UserInfo userInfo);

    ResponseEntity<?> login(AuthRequest authRequest);

    ResponseEntity<?> getAllUsers();

    ResponseEntity<?> updateUser(UserInfo userInfo);

    ResponseEntity<?> checkToken();
}
