package com.mtg.collection.serviceImpl;

import com.mtg.collection.entity.Role;
import com.mtg.collection.entity.Status;
import com.mtg.collection.entity.UserInfo;
import com.mtg.collection.repository.UserInfoRepository;
import com.mtg.collection.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.Objects;
import java.util.Optional;
@Service
public class UserInfoServiceImpl implements UserInfoService
{
    Logger log = LoggerFactory.getLogger(UserInfoServiceImpl.class);
    @Autowired
    UserInfoRepository userInfoRepository;

    @Override
    public ResponseEntity<?> addNewUser(UserInfo userInfo)
    {
        try
        {
            if(!validateUser(userInfo))
            {
                return new ResponseEntity<>("{\"message\":\"Missing Required Data.\"}", HttpStatus.BAD_REQUEST);
            }
            Optional<UserInfo> db = userInfoRepository.findByEmail(userInfo.getEmail());
            if(db.isPresent())
            {
                return new ResponseEntity<>("{\"message\":\"Email Already Exists.\"}", HttpStatus.CONFLICT);
            }
            userInfo.setPassword(userInfo.getPassword());
            userInfo.setStatus(Status.INACTIVE);
            userInfo.setRole(Role.USER);
            userInfo.setEmail(userInfo.getEmail().toLowerCase());
            userInfoRepository.save(userInfo);
            return new ResponseEntity<>("{\"message\":\"User Successfully Added.\"}", HttpStatus.OK);
        }
        catch (Exception e)
        {
            log.error("Exception in addNewUser : {}", e);
        }
        return new ResponseEntity<>("{\"message\":\"Something went wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Boolean validateUser(UserInfo userInfo)
    {
        return !Objects.isNull(userInfo) && StringUtils.hasText(userInfo.getUsername())
                && StringUtils.hasText(userInfo.getEmail()) && StringUtils.hasText(userInfo.getPassword());
    }
}
