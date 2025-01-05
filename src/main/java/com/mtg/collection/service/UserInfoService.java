package com.mtg.collection.service;

import com.mtg.collection.entity.UserInfo;
import com.mtg.collection.repository.UserInfoRepository;
import jakarta.persistence.EntityNotFoundException;
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
        return userInfoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    public void deleteUser(Long id)
    {
        if(!userInfoRepository.existsById(id))
        {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
        else
        {
            userInfoRepository.deleteById(id);
        }
    }

    public UserInfo updateUser(Long id, UserInfo updatedUser)
    {
        UserInfo existingUser = userInfoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        if(updatedUser.getUsername() != null)
        {
            existingUser.setUsername(updatedUser.getUsername());
        }
        if(updatedUser.getPassword() != null)
        {
            existingUser.setPassword(updatedUser.getPassword());
        }
        if(updatedUser.getEmail() != null)
        {
            existingUser.setEmail(updatedUser.getEmail());
        }
        if(updatedUser.getRole() != null)
        {
            existingUser.setRole(updatedUser.getRole());
        }
        if(updatedUser.getStatus() != null)
        {
            existingUser.setStatus(updatedUser.getStatus());
        }

        return userInfoRepository.save(existingUser);

    }

    public UserInfo findUserByEmail(String email)
    {
        return userInfoRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
    }
}
