package com.bananac.framework.core.util;

import org.springframework.beans.BeanUtils;

public class BeanUtil {
    public static void copyProperties(Object source,Object target){
        BeanUtils.copyProperties(source, target);
    }
}
