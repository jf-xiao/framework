package com.bananac.framework.system.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.bananac.framework.system.service.SysModularService;

/**
 * 系统模块
 * @author xiaojf 294825811@qq.com
 * 2014-12-13
 */
@Controller
public class SysModularController {
    @Resource(name="sysModularService")
    private SysModularService sysModularService;
}
