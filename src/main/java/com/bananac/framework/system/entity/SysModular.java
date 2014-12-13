package com.bananac.framework.system.entity;

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
    private java.lang.String pid ;    //上级模块
    private java.lang.String name ;    //名称
    private java.lang.String code ;    //编码
    private java.lang.String url ;    //URL
    private java.lang.Integer remark ;    //备注
    private java.lang.String creator ;    //创建人
    private java.util.Date createTime ;    //创建时间
    private java.lang.String updator ;    //修改人
    private java.util.Date updateTime ;    //修改时间
    private java.lang.Boolean logDel ;    //逻辑删除
    private java.lang.Boolean available ;    //是否可用
    private java.lang.Integer version ;    //版本号

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
     * get 上级模块
     * @return 上级模块
     */
    @Column(name = "PID", length = 64 )
    public java.lang.String getPid () {
        return this.pid ;
    }
    
    /**
     * set 上级模块
     * @param pid 上级模块
     */
    public void setPid (java.lang.String pid) {
        this.pid = pid ;
    }
            
    /**
     * get 名称
     * @return 名称
     */
    @Column(name = "NAME", length = 64 )
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
    @Column(name = "CODE", length = 64 )
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
    @Column(name = "URL", length = 128 )
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
     * get 备注
     * @return 备注
     */
    @Column(name = "REMARK")
    public java.lang.Integer getRemark () {
        return this.remark ;
    }
    
    /**
     * set 备注
     * @param remark 备注
     */
    public void setRemark (java.lang.Integer remark) {
        this.remark = remark ;
    }
            
    /**
     * get 创建人
     * @return 创建人
     */
    @Column(name = "CREATOR", length = 64 )
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
     * get 创建时间
     * @return 创建时间
     */
    @Column(name = "CREATE_TIME", length = 19 )
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
     * get 修改人
     * @return 修改人
     */
    @Column(name = "UPDATOR", length = 64 )
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
     * get 修改时间
     * @return 修改时间
     */
    @Column(name = "UPDATE_TIME", length = 19 )
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
     * get 逻辑删除
     * @return 逻辑删除
     */
    @Column(name = "LOG_DEL", length = 0 )
    public java.lang.Boolean getLogDel () {
        return this.logDel ;
    }
    
    /**
     * set 逻辑删除
     * @param logDel 逻辑删除
     */
    public void setLogDel (java.lang.Boolean logDel) {
        this.logDel = logDel ;
    }
            
    /**
     * get 是否可用
     * @return 是否可用
     */
    @Column(name = "AVAILABLE", length = 0 )
    public java.lang.Boolean getAvailable () {
        return this.available ;
    }
    
    /**
     * set 是否可用
     * @param available 是否可用
     */
    public void setAvailable (java.lang.Boolean available) {
        this.available = available ;
    }
            
    @Version
    @Column(name = "VERSION")
    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}