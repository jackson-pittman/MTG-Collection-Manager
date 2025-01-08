package com.mtg.collection.service;

import com.mtg.collection.entity.UserInfo;
import org.springframework.http.ResponseEntity;

public interface UserInfoService
{

    ResponseEntity<?> addNewUser(UserInfo userInfo);
}
