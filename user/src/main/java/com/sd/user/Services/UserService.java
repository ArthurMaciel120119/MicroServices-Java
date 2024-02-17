package com.sd.user.Services;

import com.sd.user.Producers.UserProducer;
import com.sd.user.Repository.UserRepository;
import com.sd.user.models.UserModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;

@Service
public class UserService {

    final UserRepository userRepository;
    final UserProducer userProducer;

    @Autowired
    public UserService(UserRepository userRepository, UserProducer userProducer){
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    @Transactional
    public UserModel save(UserModel userModel){
        userModel = userRepository.save(userModel);
        userProducer.publishMessageEmail(userModel);
        return userModel;
    }

}
