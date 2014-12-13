package com.bananac.framework.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bananac.framework.core.dao.impl.BaseDaoImpl;
import com.bananac.framework.system.dao.SysModularDao;
import com.bananac.framework.system.entity.SysModular;
import com.bananac.framework.system.service.SysModularService;

/**
 * 系统模块
 * @author xiaojf 294825811@qq.com
 * 2014-12-13
 */
@Service("sysModularService")
public class SysModularServiceImpl extends BaseDaoImpl<SysModular> implements SysModularService{
    @Resource(name="sysModularDao")
    private SysModularDao sysModularDao;
}
