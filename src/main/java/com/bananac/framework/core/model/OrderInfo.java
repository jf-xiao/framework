package com.bananac.framework.core.model;

/**
 * 排序
 * @author xiaojf 294825811@qq.com
 */
public class OrderInfo {
    /**降序*/
    public static final String ORDER_TYPE_DESC = "desc";
    /**升序*/
    public static final String ORDER_TYPE_ASC = "asc";
    
    /**排序类型，默认降序*/
    private String type = ORDER_TYPE_DESC;
    /**需要排序的字段*/
    private String property;
    
    public OrderInfo(String property,String type) {
        super();
        this.type = type;
        this.property = property;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
