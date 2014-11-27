package com.bananac.demo.query;

import com.bananac.framework.core.query.BaseQueryCondition;

/**
 * @author xiaojf 294825811@qq.com
 * 2014-11-27
 */
public class SysModularQueryCondition extends BaseQueryCondition {
    private java.lang.String id ;    //主键
    
    private java.util.List code ;    //编码

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public java.util.List getCode() {
        return code;
    }

    public void setCode(java.util.List code) {
        this.code = code;
    }

    
}
