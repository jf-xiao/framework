package com.bananac.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
/**
 * 系统模块
 * @author xiaojf 294825811@qq.com
 */
@Entity
@Table(name = "sys_modular")
public class SysModular implements java.io.Serializable {
    private java.lang.String id ;    //主键
    private java.lang.String name ;    //名称
    private java.lang.String code ;    //编码
    private java.lang.String url ;    //URL
    private java.lang.String parentId ;    //上级模块主键
    private java.lang.String creator ;    //创建人
    private java.lang.String updator ;    //修改人
    private java.util.Date createTime ;    //创建时间
    private java.util.Date updateTime ;    //修改时间
    private java.lang.String company ;    //公司
    private java.lang.Integer version ;    //版本号
    private java.lang.Integer logDel ;    //逻辑删除
    private java.lang.Integer enabled ;    //是否可用
    private java.lang.Integer sorted ;    //排序
    private java.lang.String remarks ;    //备注
    
    public SysModular() {
    }

        
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", unique = true, nullable = false, length = 64)
    public java.lang.String getId() {
        return this.id ;
    }
    
    public void setId(java.lang.String id) {
        this.id = id ;
    }
            
    /**
     * get 名称
     * @return 名称
     */
    @Column(name = "NAME", length = 64)
    public java.lang.String getName () {
        return this.name ;
    }
    
    /**
     * set 名称
     * @param name 名称
     */
    public void setName (java.lang.String name) {
        this.name = name ;
    }
            
    /**
     * get 编码
     * @return 编码
     */
    @Column(name = "CODE", length = 64)
    public java.lang.String getCode () {
        return this.code ;
    }
    
    /**
     * set 编码
     * @param code 编码
     */
    public void setCode (java.lang.String code) {
        this.code = code ;
    }
            
    /**
     * get URL
     * @return URL
     */
    @Column(name = "URL", length = 254)
    public java.lang.String getUrl () {
        return this.url ;
    }
    
    /**
     * set URL
     * @param url URL
     */
    public void setUrl (java.lang.String url) {
        this.url = url ;
    }
    
    /**
     * get 上级模块
     * @return URL
     */
    @Column(name = "PARENT_ID", length = 64)
    public java.lang.String getParentId() {
        return parentId;
    }

    /**
     * set 上级模块
     * @param url URL
     */
    public void setParentId(java.lang.String parentId) {
        this.parentId = parentId;
    }


    /**
     * get 创建人
     * @return 创建人
     */
    @Column(name = "CREATOR", length = 64)
    public java.lang.String getCreator () {
        return this.creator ;
    }
    
    /**
     * set 创建人
     * @param creator 创建人
     */
    public void setCreator (java.lang.String creator) {
        this.creator = creator ;
    }
            
    /**
     * get 修改人
     * @return 修改人
     */
    @Column(name = "UPDATOR", length = 64)
    public java.lang.String getUpdator () {
        return this.updator ;
    }
    
    /**
     * set 修改人
     * @param updator 修改人
     */
    public void setUpdator (java.lang.String updator) {
        this.updator = updator ;
    }
            
    /**
     * get 创建时间
     * @return 创建时间
     */
    @Column(name = "CREATE_TIME", length = 19)
    public java.util.Date getCreateTime () {
        return this.createTime ;
    }
    
    /**
     * set 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime (java.util.Date createTime) {
        this.createTime = createTime ;
    }
            
    /**
     * get 修改时间
     * @return 修改时间
     */
    @Column(name = "UPDATE_TIME", length = 19)
    public java.util.Date getUpdateTime () {
        return this.updateTime ;
    }
    
    /**
     * set 修改时间
     * @param updateTime 修改时间
     */
    public void setUpdateTime (java.util.Date updateTime) {
        this.updateTime = updateTime ;
    }
            
    /**
     * get 公司
     * @return 公司
     */
    @Column(name = "COMPANY", length = 64)
    public java.lang.String getCompany () {
        return this.company ;
    }
    
    /**
     * set 公司
     * @param company 公司
     */
    public void setCompany (java.lang.String company) {
        this.company = company ;
    }
            
    @Version
    @Column(name = "VERSION")
    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
            
    /**
     * get 逻辑删除
     * @return 逻辑删除
     */
    @Column(name = "LOG_DEL", length = 10)
    public java.lang.Integer getLogDel () {
        return this.logDel ;
    }
    
    /**
     * set 逻辑删除
     * @param logDel 逻辑删除
     */
    public void setLogDel (java.lang.Integer logDel) {
        this.logDel = logDel ;
    }
            
    /**
     * get 是否可用
     * @return 是否可用
     */
    @Column(name = "ENABLED", length = 10)
    public java.lang.Integer getEnabled () {
        return this.enabled ;
    }
    
    /**
     * set 是否可用
     * @param enabled 是否可用
     */
    public void setEnabled (java.lang.Integer enabled) {
        this.enabled = enabled ;
    }
            
    /**
     * get 排序
     * @return 排序
     */
    @Column(name = "SORTED", length = 10)
    public java.lang.Integer getSorted () {
        return this.sorted ;
    }
    
    /**
     * set 排序
     * @param sorted 排序
     */
    public void setSorted (java.lang.Integer sorted) {
        this.sorted = sorted ;
    }
            
    /**
     * get 备注
     * @return 备注
     */
    @Column(name = "REMARKS", length = 254)
    public java.lang.String getRemarks () {
        return this.remarks ;
    }
    
    /**
     * set 备注
     * @param remarks 备注
     */
    public void setRemarks (java.lang.String remarks) {
        this.remarks = remarks ;
    }

}