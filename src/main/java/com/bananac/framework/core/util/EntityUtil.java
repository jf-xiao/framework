package com.bananac.framework.core.util;

import java.lang.reflect.Field;

import javax.persistence.Id;

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
