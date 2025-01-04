package com.mtg.collection.service;

import com.mtg.collection.entity.UserInfo;
import com.mtg.collection.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService
{
    @Autowired
    private UserInfoRepository userInfoRepository;

    public List<UserInfo> getAllUsers()
    {
        return userInfoRepository.findAll();
    }

    public void saveUser(UserInfo userInfo)
    {
        userInfoRepository.save(userInfo);
    }

    public UserInfo getUserById(Long id)
    {
        return userInfoRepository.getById(id);
    }
}
