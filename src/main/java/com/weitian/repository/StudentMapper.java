package com.weitian.repository;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2018/11/21.
 */
public interface StudentMapper extends JpaRepository<Student,Integer>{
    Student findByName(String name);
}
