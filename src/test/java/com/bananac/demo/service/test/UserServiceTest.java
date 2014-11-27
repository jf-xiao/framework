package com.bananac.demo.service.test;

import java.io.IOException;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bananac.demo.service.UserService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:applicationContext-jdbc.xml","classpath:applicationContext-beans.xml"})
public class UserServiceTest {
    @Resource(name="userService")
    UserService service;

/*    @Test
    public void save() {
        service.save();
    }*/
    
    /*@Test
    public void userCount() {
        service.save();
    }*/
    
    /*@Test
    public void test() throws IOException{
        service.test();
    }*/
}
