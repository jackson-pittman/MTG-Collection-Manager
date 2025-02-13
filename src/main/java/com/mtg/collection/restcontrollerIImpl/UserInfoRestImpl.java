package com.mtg.collection.restcontrollerIImpl;

import com.mtg.collection.entity.AuthRequest;
import com.mtg.collection.entity.UserInfo;
import com.mtg.collection.restcontroller.UserInfoRest;
import com.mtg.collection.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserInfoRestImpl implements UserInfoRest
{
    Logger log = LoggerFactory.getLogger(UserInfoRestImpl.class);

    @Autowired
    UserInfoService userInfoService;

    Map<String,String> map = new HashMap<>();
    @Override
    public ResponseEntity<?> addNewUser(UserInfo userInfo)
    {
        try
        {
            return userInfoService.addNewUser(userInfo);
        }
        catch (Exception e)
        {
            log.error("Exception in addNewUser : {} ",e);
            map.put("message", "Something went wrong");
        }
        return new ResponseEntity<>("{\"message\":\"Something went wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> login(AuthRequest authRequest)
    {
        try
        {
            return userInfoService.login(authRequest);
        }
        catch (Exception e)
        {
            log.error("Exception in login : {} ",e);
            map.put("message", "Something went wrong");
        }
        return new ResponseEntity<>("{\"message\":\"Something went wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> updateUser(UserInfo userInfo)
    {
        try
        {
            return userInfoService.updateUser(userInfo);
        }
        catch (Exception e)
        {
            log.error("Exception in updateUserStatus : {} ",e);
            map.put("message", "Something went wrong");
        }
        return new ResponseEntity<>("{\"message\":\"Something went wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> getAllUsers()
    {
        try
        {
            return userInfoService.getAllUsers();
        }
        catch (Exception e)
        {
            log.error("Exception in getAllUsers : {} ",e);
            map.put("message", "Something went wrong");
        }
        return new ResponseEntity<>("{\"message\":\"Something went wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> checkToken()
    {
        return userInfoService.checkToken();
    }
}
