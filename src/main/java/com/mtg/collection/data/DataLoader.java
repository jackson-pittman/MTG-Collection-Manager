package com.mtg.collection.data;

import com.mtg.collection.entity.Role;
import com.mtg.collection.entity.Status;
import com.mtg.collection.entity.UserInfo;
import com.mtg.collection.repository.UserInfoRepository;
import org.hibernate.event.spi.PreInsertEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        if(!userInfoRepository.findByEmail("admin").isPresent())
        {
            UserInfo userInfo = new UserInfo();
            userInfo.setEmail("admin@email.com");
            userInfo.setDeletable(false);
            userInfo.setStatus(Status.ACTIVE);
            userInfo.setPassword(encoder.encode("admin"));
            userInfo.setRole(Role.ADMIN);
            userInfoRepository.save(userInfo);
        }
        System.out.println("=========== You can login with : email (admin@email.com) and password (admin) ===========");
    }
}
