package com.bananac.framework.core.model;

public class QueryCondition {
    public static final String OPERATE_TYPE_EQ = "eq";
    public static final String OPERATE_TYPE_ALLEQ = "alleq";
    public static final String OPERATE_TYPE_GT = "gt";
    public static final String OPERATE_TYPE_GE = "ge";
    public static final String OPERATE_TYPE_LT = "lt";
    public static final String OPERATE_TYPE_LE = "le";
    public static final String OPERATE_TYPE_BETWEEN = "between";
    public static final String OPERATE_TYPE_LIKE = "like";
    public static final String OPERATE_TYPE_IN = "in";
    public static final String OPERATE_TYPE_AND = "and";
    public static final String OPERATE_TYPE_OR = "or";
    
    /**属性*/
    private String property;
    /**属性值*/
    private Object value;
    /**类型*/
    private String operate = OPERATE_TYPE_EQ;
    public QueryCondition() {
        super();
    }
    public QueryCondition(String property, Object value, String operate) {
        super();
        this.property = property;
        this.value = value;
        this.operate = operate;
    }
    public String getProperty() {
        return property;
    }
    public void setProperty(String property) {
        this.property = property;
    }
    public Object getValue() {
        return value;
    }
    public void setValue(Object value) {
        this.value = value;
    }
    public String getOperate() {
        return operate;
    }
    public void setOperate(String operate) {
        this.operate = operate;
    }
    
}
