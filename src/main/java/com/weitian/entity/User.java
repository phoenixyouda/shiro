package com.weitian.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Administrator on 2018/11/20.
 */
@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private Integer uid;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_role",joinColumns = {@JoinColumn(name="uid")},inverseJoinColumns = {@JoinColumn(name="rid")})
    private List<Role> roleList;


}
