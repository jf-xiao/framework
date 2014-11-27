package com.bananac.framework.core.dao;

import java.io.Serializable;
import java.util.List;

import com.bananac.framework.core.model.OrderInfo;
import com.bananac.framework.core.model.PageInfo;
import com.bananac.framework.core.query.BaseQueryCondition;
import com.bananac.framework.core.query.BaseQueryItem;

/**
 * 公共数据访问接口
 * @author xiaojf 294825811@qq.com
 * @param <T>
 * 2014-11-24
 */
public interface BaseDao<T> {
    
    /**
     * 保存
     * @param entity 实体对象
     * @return 实体对象
     * 2014-11-24
     */
    public T save(T entity);
    
    /**
     * 保存
     * @param entities 实体对象集合
     * @return 实体对象集合
     * 2014-11-24
     */
    public List<T> save(List<T> entities);
    
    /**
     * 获取
     * @param id 主键
     * @return 实体对象
     * 2014-11-24
     */
    public T get(Serializable id);
    
    /**
     * 获取
     * @param ids 主键集合
     * @return
     * 2014-11-24
     */
    public List<T> get(List<Serializable> ids);
    
    /**
     * 获取
     * @param ids 主键集合
     * @param orders 排序对象集合
     * @return
     * 2014-11-24
     */
    public List<T> get(List<Serializable> ids,List<OrderInfo> orders);
    
    /**
     * 获取所有记录
     * @return
     * 2014-11-24
     */
    public List<T> getAll();
    
    /**
     * 获取所有记录
     * @param orders 排序
     * @param page 分页
     * @return
     * 2014-11-24
     */
    public List<T> getAll(List<OrderInfo> orders,PageInfo page);
    
    /**
     * 查询
     * @param hql 查询语句
     * @param params 查询参数
     * @return
     * 2014-11-24
     */
    public List<T> find(String hql , Object... params);
    
    /**
     * 查询
     * @param hql 查询语句
     * @param page 分页
     * @param params 查询参数
     * @return
     * 2014-11-24
     */
    public List<T> find(String hql , PageInfo page,Object... params);
    
    /**
     * 查询
     * @param entity 实体对象
     * @return
     * 2014-11-24
     */
    public List<T> findByExample(T entity);
    
    /**
     * 查询
     * @param entity
     * @param orders
     * @param page
     * @return
     * 2014-11-24
     */
    public List<T> findByExample(T entity,List<OrderInfo> orders,PageInfo page);
        
    /**
     * 删除
     * @param id 主键
     * 2014-11-24
     */
    public void delete(Serializable id);
    
    /**
     * 删除
     * @param ids 主键集合
     * 2014-11-24
     */
    public void delete(List<Serializable> ids);
    
    /**
     * 判断实体对象是否存在
     * @param id 主键
     * @return
     * 2014-11-24
     */
    public boolean exists(Serializable id);
    
    /**
     * 总记录数
     * @return
     * 2014-11-24
     */
    public int getRowCount();
    
    /**
     * 封装Hibernate的NamedQuery, 自动封装查询条件和结果,注意XXX.hbm.xml中填写的应该是原生SQL查询语句
     * @param condition 查询条件
     * @param item 查询结果
     * @return
     * 2014-11-26
     */
    public <ITEM extends BaseQueryItem> List<ITEM> findByNamedQuery(BaseQueryCondition condition ,Class<ITEM> item);
    
    /**
     * 封装Hibernate的NamedQuery, 自动封装查询条件和结果,注意XXX.hbm.xml中填写的应该是原生SQL查询语句
     * @param condition 查询条件
     * @param item 查询结果
     * @param page 分页
     * @return
     * 2014-11-26
     */
    public <ITEM extends BaseQueryItem> List<ITEM> findByNamedQuery(BaseQueryCondition condition ,Class<ITEM> item,PageInfo page);
    
}
