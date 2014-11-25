package com.bananac.framework.core.util;

import java.lang.reflect.Field;

import javax.persistence.Id;

/**
 * 实体工具类
 * @author xiaojf 294825811@qq.com
 * 2014-11-24
 */
public class EntityUtil {
    public static String getPkName(Class clazz) {
        String pkName = "";
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.isAnnotationPresent(Id.class);
            pkName = field.getName();
            break;
        }
        return pkName;
    }
}
