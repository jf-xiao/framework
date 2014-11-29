package com.bananac.demo.demo.controller;

import java.io.IOException;

import javax.annotation.Resource;

import com.bananac.demo.service.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource(name = "userService")
    private UserService service;

    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public ModelAndView hello2() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", "HelloMVC");
        mv.setViewName("users");
        
        try {
            service.test();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mv;
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ModelAndView count() throws IOException {

        //int c = service.userCount();
        //service.userCount();
        //service.test();
        service.test2();

        ModelAndView mv = new ModelAndView();
        mv.addObject("message", null);
        mv.setViewName("users");
        return mv;
    }
    
    @RequestMapping(value = "/count2", method = RequestMethod.GET)
    public ModelAndView count2() throws IOException {

        //int c = service.userCount();
        //service.userCount();
        //service.test();
        service.test3();

        ModelAndView mv = new ModelAndView();
        mv.addObject("message", null);
        mv.setViewName("users");
        return mv;
    }
    
}