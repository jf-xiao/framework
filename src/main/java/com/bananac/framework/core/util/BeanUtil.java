package com.bananac.framework.core.util;

import org.springframework.beans.BeanUtils;

/**
 * 对象工具类
 * @author xiaojf 294825811@qq.com
 * 2014-11-24
 */
public class BeanUtil {
    public static void copyProperties(Object source,Object target){
        BeanUtils.copyProperties(source, target);
    }
}
