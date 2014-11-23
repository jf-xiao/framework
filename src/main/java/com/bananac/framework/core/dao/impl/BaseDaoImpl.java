package com.bananac.framework.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.Id;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.bananac.framework.core.dao.BaseDao;
import com.bananac.framework.core.model.OrderInfo;
import com.bananac.framework.core.model.PageInfo;
import com.bananac.framework.core.model.QueryCondition;
import com.bananac.framework.core.util.BeanUtil;
import com.bananac.framework.core.util.EntityUtil;

public class BaseDaoImpl<T> implements BaseDao<T> {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory; // 会话工厂

    private Class<T> clazz;

    public BaseDaoImpl() {
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }
    
    public Session getSession(){
        return this.sessionFactory.getCurrentSession();
    }
    
    public void flush(){
        this.getSession().flush();
    }
    
    public void clear(){
        this.getSession().clear();
    }
    
    public void close(){
        this.getSession().close();
    }
    
    public void evict(T entity){
        this.getSession().evict(entity);
    }
    
    public T converEntity(Object entity,Class<?> cls){
        Object instance = null;
        try {
            instance = cls.newInstance();
            BeanUtil.copyProperties(entity, instance);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        
        return (T) instance;
    }

    @Override
    public T save(T entity) {
        Object obj = this.getSession().merge(entity);
        this.flush();
        return this.converEntity(entity, clazz);
    }

    @Override
    public List<T> save(List<T> entities) {
        List<T> list = new ArrayList<T>();
        if(entities != null){
            for(T t : entities){
                T obj = this.save(t);
                list.add(obj);
            }
        }
        return list;
    }

    @Override
    public T get(Serializable id) {
        Object entity = this.getSession().get(clazz, id);
        return this.converEntity(entity, clazz);
    }

    @Override
    public List<T> get(List<Serializable> ids) {
        return get(ids,null);
    }
    
    @Override
    public List<T> get(List<Serializable> ids, List<OrderInfo> orders) {
        List<T> list = new ArrayList<T>();
        if(ids != null){
            Criteria criteria = this.getSession().createCriteria(clazz);
            criteria.add(Restrictions.in(EntityUtil.getPkName(clazz), ids.toArray()));
            if(orders != null){
                for(OrderInfo order : orders){
                    if(OrderInfo.ORDER_TYPE_ASC.equals(order.getType())){
                        criteria.addOrder(Order.asc(order.getProperty()));
                    }else if(OrderInfo.ORDER_TYPE_DESC.equals(order.getType())){
                        criteria.addOrder(Order.desc(order.getProperty()));
                    }
                }
            }
            list = criteria.list();
        }
        
        List<T> result = new ArrayList<T>();
        for(T t : list){
            result.add(this.converEntity(t, clazz));
        }
        return result;
    }

    @Override
    public List<T> getAll() {
        return getAll(null,null);
    }

    @Override
    public List<T> getAll(List<OrderInfo> orders, PageInfo page) {
        List<T> list = new ArrayList<T>();

        Criteria criteria = this.getSession().createCriteria(clazz);
        if(orders != null){
            for(OrderInfo order : orders){
                if(OrderInfo.ORDER_TYPE_ASC.equals(order.getType())){
                    criteria.addOrder(Order.asc(order.getProperty()));
                }else if(OrderInfo.ORDER_TYPE_DESC.equals(order.getType())){
                    criteria.addOrder(Order.desc(order.getProperty()));
                }
            }
        }
        if(page != null){
            criteria.setFirstResult((page.getPage()-1)*page.getRows()).setMaxResults(page.getRows());
        }
        
        list = criteria.list();
        List<T> result = new ArrayList<T>();
        for(T t : list){
            result.add(this.converEntity(t, clazz));
        }
        return result;
    }

    @Override
    public List<T> find(String hql, Object... params) {
        Query query = this.getSession().createQuery(hql);
        if(params != null){
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
        }
        List<T> list = query.list();
        List<T> result = new ArrayList<T>();
        for(T t : list){
            result.add(this.converEntity(t, clazz));
        }
        return result;
    }

    @Override
    public List<T> find(String hql, PageInfo page, Object... params) {
        Query query = this.getSession().createQuery(hql);
        if(params != null){
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
        }
        
        if(page != null){
            query.setFirstResult((page.getPage()-1)*page.getRows()).setMaxResults(page.getRows());
        }
        
        List<T> list = query.list();
        List<T> result = new ArrayList<T>();
        for(T t : list){
            result.add(this.converEntity(t, clazz));
        }
        return query.list();
    }

    @Override
    public List<T> findByExample(T entity) {
        return findByExample(entity,null,null);
    }

    @Override
    public List<T> findByExample(T entity, List<OrderInfo> orders, PageInfo page) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(clazz);
        criteria.add(Example.create(entity));
        
        if(orders != null){
            for(OrderInfo order : orders){
                if(OrderInfo.ORDER_TYPE_ASC.equals(order.getType())){
                    criteria.addOrder(Order.asc(order.getProperty()));
                }else if(OrderInfo.ORDER_TYPE_DESC.equals(order.getType())){
                    criteria.addOrder(Order.desc(order.getProperty()));
                }
            }
        }
        if(page != null){
            criteria.setFirstResult((page.getPage()-1)*page.getRows()).setMaxResults(page.getRows());
        }
        
        List<T> list = criteria.list();
        List<T> result = new ArrayList<T>();
        for(T t : list){
            result.add(this.converEntity(t, clazz));
        }
        return result;
    }

    @Override
    public void delete(Serializable id) {
        T t = (T) this.getSession().get(clazz, id);
        this.getSession().delete(t);
        this.flush();
        this.evict(t);
    }

    @Override
    public void delete(List<Serializable> ids) {
        for(Serializable id : ids){
            delete(id);
        }
    }

    @Override
    public boolean exists(Serializable id) {
        return this.get(id) == null ? false : true;
    }

    @Override
    public int getRowCount() {
        return this.getAll().size();
    }

}