package com.weitian.service;

import com.weitian.entity.User;

/**
 * Created by Administrator on 2018/11/20.
 */


public interface UserService {
    public User findByUserName(String username);
}
