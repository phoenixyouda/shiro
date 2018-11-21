package com.weitian.repository;

import com.weitian.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2018/11/20.
 */

public interface UserRepository extends JpaRepository<User,Integer> {

    public User findByUsername(String userName);

}
