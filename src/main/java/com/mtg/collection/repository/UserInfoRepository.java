package com.mtg.collection.repository;

import com.mtg.collection.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long>
{
    Optional<UserInfo> findByEmail(String email);
    List<UserInfo> getAllUsers(String email);
}
