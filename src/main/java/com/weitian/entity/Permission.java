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
public class Permission {
    @Id
    @GeneratedValue
    private Integer pid;
    private String name;
    private String url;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="role_permission",joinColumns = {@JoinColumn(name="pid")},inverseJoinColumns = {@JoinColumn(name="rid")})
    private List<Role> roleList=new ArrayList<Role>();

}
