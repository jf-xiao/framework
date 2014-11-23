package com.bananac.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bananac.demo.entity.Users;
import com.bananac.framework.core.dao.impl.BaseDaoImpl;

@Repository("userDao")
public class UsersDAO extends BaseDaoImpl<Users>{

    public List<Users> getAllUser(){
        String hsql="from users";
        return this.find(hsql);
    }
}