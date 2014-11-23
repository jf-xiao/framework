package com.bananac.framework.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.bananac.framework.core.model.OrderInfo;
import com.bananac.framework.core.model.PageInfo;
import com.bananac.framework.core.model.QueryCondition;

public interface BaseDao<T> {
    public T save(T entity);
    public List<T> save(List<T> entities);
    
    public T get(Serializable id);
    public List<T> get(List<Serializable> ids);
    public List<T> get(List<Serializable> ids,List<OrderInfo> orders);
    
    public List<T> getAll();
    public List<T> getAll(List<OrderInfo> orders,PageInfo page);
    
    public List<T> find(String hql , Object... params);
    public List<T> find(String hql , PageInfo page,Object... params);
    
    public List<T> findByExample(T entity);
    public List<T> findByExample(T entity,List<OrderInfo> orders,PageInfo page);
        
    public void delete(Serializable id);
    public void delete(List<Serializable> ids);
    
    public boolean exists(Serializable id);
    
    public int getRowCount();
    
}
