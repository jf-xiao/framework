package com.bananac.demo.demo.controller;

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
        return mv;
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ModelAndView count() {

        //int c = service.userCount();
        service.save();

        ModelAndView mv = new ModelAndView();
        mv.addObject("message", null);
        mv.setViewName("users");
        return mv;
    }
}