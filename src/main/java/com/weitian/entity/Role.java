package com.weitian.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/11/20.
 */
@Entity
@Data
public class Role {
    @Id
    @GeneratedValue
    private Integer rid;
    private String name;
    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(name="role_permission",joinColumns = {@JoinColumn(name="rid")},inverseJoinColumns = {@JoinColumn(name="pid")})
    private List<Permission> permissionList=new ArrayList<>(  );
    @ManyToMany
    @JoinTable(name="user_role",joinColumns = {@JoinColumn(name="rid")},inverseJoinColumns = {@JoinColumn(name="uid")})
    private List<User> userList=new ArrayList<>(  );


}
