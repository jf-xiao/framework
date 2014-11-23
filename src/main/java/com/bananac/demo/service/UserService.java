package com.bananac.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bananac.demo.dao.*;
import com.bananac.demo.entity.Users;
@Service("userService")
public class UserService {
    @Resource(name="userDao")
    private UsersDAO userDao;

    public int userCount() {
        return userDao.getAllUser().size();
    }
    
    public void save(){
        Users users1 = new Users();
        users1.setId(1);
        users1.setAge(11);
        users1.setNice_name("肖建锋3");
        
        Users users2 = new Users();
        users2.setAge(11);
        users2.setNice_name("肖建锋4");
        
        List list = new ArrayList();
        //list.add(users1);
        //list.add(users2);
        list.add(1);
        list.add(2);
        list.add(3);
        userDao.get(list);
    }

}