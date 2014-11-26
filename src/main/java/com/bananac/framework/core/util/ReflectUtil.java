package com.bananac.framework.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.bananac.demo.entity.SysModular;

/**
 * 反射工具类
 * @author xiaojf 294825811@qq.com
 * 2014-11-26
 */
public class ReflectUtil {
    
    /**
     * 根据class实例化对象,如<br/>
     * Object obj = ReflectUtil.newInstance(SysModular.class);
     * @param clazz
     * @return
     * 2014-11-26
     */
    public static Object newInstance(Class<?> clazz){
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    /**
     * 获取类名称, 如com.bananac.demo.entity.SysModular 的类名称就是  SysModular
     * @param clazz
     * @return
     */
    public static String getName(Class clazz){
        String path = clazz.getName();
        String name = path.substring(path.lastIndexOf("."));
        if(name.startsWith(".")){
            name = path.substring(path.lastIndexOf(".")+1);
        }
        return name;
    }
    
    /**
     * 获取类的类路径, 如 com.bananac.demo.entity.SysModular 的类路径就是  com.bananac.demo.entity
     * @param clazz
     * @return
     */
    public static String getClasspath(Class clazz){
        String path = clazz.getName();
        return path.substring(0,path.lastIndexOf("."));
    }
    
    /**
     * 指定某个对象的属性名获取属性值,如</br>
     * SysModular modular = new SysModular();</br>
     * modular.setId("xiaojf");</br>
     * String id = ReflectUtil.getFieldValue(modular, "id")+"";</br>
     * System.out.println(id);</br>
     * @param obj 对象
     * @param fieldName 属性名
     * @return 属性值
     */
    public static Object getFieldValue(Object obj , String fieldName){
        try {
            Class clazz = obj.getClass();
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * 获取某个对象是属性类型,如
     * SysModular modular = new SysModular();
     * modular.setId("xiaojf");
     * String clazz = ReflectUtil.getFieldType(modular, "id");
     * System.out.println(clazz);
     * @param obj
     * @param fieldName
     * @return
     * 2014-11-27
     */
    public static String getFieldType(Object obj , String fieldName){
        try {
            Class<?> clazz = obj.getClass();
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.getType().getName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * 通过指定属性名称, 设置某个对象的属性值,如</br>
     * SysModular modular = new SysModular();</br>
     * ReflectUtil.setFieldValue(modular, "id", "xiaojf");</br>
     * System.out.println(ReflectUtil.getFieldValue(modular, "id"));</br>
     * @param obj 对象
     * @param fieldName 属性名称
     * @param value 属性值
     */
    public static void setFieldValue(Object obj , String fieldName,Object value){
        try {
            Class clazz = obj.getClass();
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 通过指定方法名,参数类型,参数值调用某对象方法,如</br>
     * SysModular modular = new SysModular();</br>
     *  modular.setId("xiaojf");</br>
     *  ReflectUtil.invokeObjMethod(modular, "setId",new Class[]{String.class},new String[]{"5555"});</br>
     *  System.out.println(modular.getId());</br>
     * @param obj
     * @param methodName
     * @param paramTypes
     * @param params
     */
    public static void invokeObjMethod(Object obj,String methodName,Class[] paramTypes,Object[] params){
        try {
            Class clazz = obj.getClass();
            Method method = clazz.getDeclaredMethod(methodName, paramTypes);
            method.setAccessible(true);
            method.invoke(obj, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        SysModular modular = new SysModular();
        modular.setId("xiaojf");
        String clazz = ReflectUtil.getFieldType(modular, "createTime");
        System.out.println(clazz);
    }
}
