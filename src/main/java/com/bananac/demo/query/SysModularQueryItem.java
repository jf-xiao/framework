package com.bananac.demo.query;

import com.bananac.framework.core.query.BaseQueryItem;

/**
 * @author xiaojf 294825811@qq.com
 * 2014-11-27
 */
public class SysModularQueryItem extends BaseQueryItem {
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
    public java.lang.String getId() {
        return id;
    }
    public void setId(java.lang.String id) {
        this.id = id;
    }
    public java.lang.String getName() {
        return name;
    }
    public void setName(java.lang.String name) {
        this.name = name;
    }
    public java.lang.String getCode() {
        return code;
    }
    public void setCode(java.lang.String code) {
        this.code = code;
    }
    public java.lang.String getUrl() {
        return url;
    }
    public void setUrl(java.lang.String url) {
        this.url = url;
    }
    public java.lang.String getParentId() {
        return parentId;
    }
    public void setParentId(java.lang.String parentId) {
        this.parentId = parentId;
    }
    public java.lang.String getCreator() {
        return creator;
    }
    public void setCreator(java.lang.String creator) {
        this.creator = creator;
    }
    public java.lang.String getUpdator() {
        return updator;
    }
    public void setUpdator(java.lang.String updator) {
        this.updator = updator;
    }
    public java.util.Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }
    public java.util.Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }
    public java.lang.String getCompany() {
        return company;
    }
    public void setCompany(java.lang.String company) {
        this.company = company;
    }
    public java.lang.Integer getVersion() {
        return version;
    }
    public void setVersion(java.lang.Integer version) {
        this.version = version;
    }
    public java.lang.Integer getLogDel() {
        return logDel;
    }
    public void setLogDel(java.lang.Integer logDel) {
        this.logDel = logDel;
    }
    public java.lang.Integer getEnabled() {
        return enabled;
    }
    public void setEnabled(java.lang.Integer enabled) {
        this.enabled = enabled;
    }
    public java.lang.Integer getSorted() {
        return sorted;
    }
    public void setSorted(java.lang.Integer sorted) {
        this.sorted = sorted;
    }
    public java.lang.String getRemarks() {
        return remarks;
    }
    public void setRemarks(java.lang.String remarks) {
        this.remarks = remarks;
    }
    @Override
    public String toString() {
        return "SysModularQueryItem [id=" + id + ", name=" + name + ", code=" + code + ", url=" + url + ", parentId="
                + parentId + ", creator=" + creator + ", updator=" + updator + ", createTime=" + createTime
                + ", updateTime=" + updateTime + ", company=" + company + ", version=" + version + ", logDel=" + logDel
                + ", enabled=" + enabled + ", sorted=" + sorted + ", remarks=" + remarks + "]";
    }
    
}
