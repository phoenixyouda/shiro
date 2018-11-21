package com.weitian.service.impl;

import com.weitian.repository.UserRepository;
import com.weitian.entity.User;
import com.weitian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/11/20.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsername( username );
    }
}
