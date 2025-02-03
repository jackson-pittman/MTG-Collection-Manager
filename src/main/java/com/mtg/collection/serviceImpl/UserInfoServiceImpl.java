package com.mtg.collection.serviceImpl;

import com.mtg.collection.entity.AuthRequest;
import com.mtg.collection.entity.Role;
import com.mtg.collection.entity.Status;
import com.mtg.collection.entity.UserInfo;
import com.mtg.collection.filter.JwtAuthFilter;
import com.mtg.collection.jwtService.JwtService;
import com.mtg.collection.jwtService.UserInfoDetails;
import com.mtg.collection.repository.UserInfoRepository;
import com.mtg.collection.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtAuthFilter jwtAuthFilter;

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
            userInfo.setPassword(encoder.encode(userInfo.getPassword()));
            userInfo.setStatus(Status.ACTIVE);
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

    @Override
    public ResponseEntity<?> login(AuthRequest authRequest)
    {
        try
        {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail().toLowerCase(), authRequest.getPassword()));
            if(auth != null && auth.isAuthenticated())
            {
                UserInfoDetails userInfoDetails = (UserInfoDetails) auth.getPrincipal();
                if(userInfoDetails.getStatus() == Status.ACTIVE)
                {
                    return new ResponseEntity<>("{\"token\":\""+jwtService.generateToken(authRequest.getEmail().toLowerCase())+"\"}", HttpStatus.OK);
                }
                else
                {
                    return new ResponseEntity<>("{\"message\":\"Wait for admin approval\"}", HttpStatus.BAD_REQUEST);
                }
            }
            else
            {
                throw new UsernameNotFoundException("Invalid user request");
            }
        }
        catch (BadCredentialsException ex)
        {
            return new ResponseEntity<>("{\"message\":\"Invalid credentials\"}", HttpStatus.UNAUTHORIZED);
        }
        catch (UsernameNotFoundException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            log.error("Exception in login : {}", e);
        }
        return new ResponseEntity<>("{\"message\":\"Something went wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Boolean validateUser(UserInfo userInfo)
    {
        return !Objects.isNull(userInfo) && StringUtils.hasText(userInfo.getUsername())
                && StringUtils.hasText(userInfo.getEmail()) && StringUtils.hasText(userInfo.getPassword());
    }
}
