package com.bananac.framework.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.bananac.framework.core.dao.BaseDao;
import com.bananac.framework.core.model.OrderInfo;
import com.bananac.framework.core.model.PageInfo;
import com.bananac.framework.core.query.BaseQueryCondition;
import com.bananac.framework.core.query.BaseQueryItem;
import com.bananac.framework.core.query.NamedQueryUtil;
import com.bananac.framework.core.util.BeanUtil;
import com.bananac.framework.core.util.CamelCaseUtil;
import com.bananac.framework.core.util.EntityUtil;
import com.bananac.framework.core.util.ReflectUtil;
import com.bananac.framework.core.util.XmlParseUtil;

/**
 * 公共数据访问实现类
 * 
 * @author xiaojf 294825811@qq.com
 * @param <T>
 *            2014-11-24
 */
public class BaseDaoImpl<T> implements BaseDao<T> {
    private static Logger logger = Logger.getLogger(BaseDaoImpl.class);
    
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory; // 会话工厂

    private Class<T> clazz;

    public BaseDaoImpl() {
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

    /**
     * 获取Session
     * 
     * @return 2014-11-24
     */
    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    /**
     * 刷新缓存,与数据库同步 2014-11-24
     */
    public void flush() {
        this.getSession().flush();
    }

    /**
     * 清除缓存 2014-11-24
     */
    public void clear() {
        this.getSession().clear();
    }

    /**
     * 关闭Session会话 2014-11-24
     */
    public void close() {
        this.getSession().close();
    }

    /**
     * 将对象从缓存域中移除
     * 
     * @param entity
     *            2014-11-24
     */
    public void evict(T entity) {
        this.getSession().evict(entity);
    }

    /**
     * 复制对象
     * 
     * @param entity
     *            实体对象
     * @param cls
     *            目标Class类型
     * @return 2014-11-24
     */
    public T converEntity(Object entity, Class<?> cls) {
        Object instance = null;
        try {
            instance = cls.newInstance();
            BeanUtil.copyProperties(entity, instance);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
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
        if (entities != null) {
            for (T t : entities) {
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
        return get(ids, null);
    }

    @Override
    public List<T> get(List<Serializable> ids, List<OrderInfo> orders) {
        List<T> list = new ArrayList<T>();
        if (ids != null) {
            Criteria criteria = this.getSession().createCriteria(clazz);
            criteria.add(Restrictions.in(EntityUtil.getPkName(clazz), ids.toArray()));
            if (orders != null) {
                for (OrderInfo order : orders) {
                    if (OrderInfo.ORDER_TYPE_ASC.equals(order.getType())) {
                        criteria.addOrder(Order.asc(order.getProperty()));
                    } else if (OrderInfo.ORDER_TYPE_DESC.equals(order.getType())) {
                        criteria.addOrder(Order.desc(order.getProperty()));
                    }
                }
            }
            list = criteria.list();
        }

        List<T> result = new ArrayList<T>();
        for (T t : list) {
            result.add(this.converEntity(t, clazz));
        }
        return result;
    }

    @Override
    public List<T> getAll() {
        return getAll(null, null);
    }

    @Override
    public List<T> getAll(List<OrderInfo> orders, PageInfo page) {
        List<T> list = new ArrayList<T>();

        Criteria criteria = this.getSession().createCriteria(clazz);
        if (orders != null) {
            for (OrderInfo order : orders) {
                if (OrderInfo.ORDER_TYPE_ASC.equals(order.getType())) {
                    criteria.addOrder(Order.asc(order.getProperty()));
                } else if (OrderInfo.ORDER_TYPE_DESC.equals(order.getType())) {
                    criteria.addOrder(Order.desc(order.getProperty()));
                }
            }
        }
        if (page != null) {
            criteria.setFirstResult((page.getPage() - 1) * page.getRows()).setMaxResults(page.getRows());
        }

        list = criteria.list();
        List<T> result = new ArrayList<T>();
        for (T t : list) {
            result.add(this.converEntity(t, clazz));
        }
        return result;
    }

    @Override
    public List<T> find(String hql, Object... params) {
        Query query = this.getSession().createQuery(hql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
        }
        List<T> list = query.list();
        List<T> result = new ArrayList<T>();
        for (T t : list) {
            result.add(this.converEntity(t, clazz));
        }
        return result;
    }

    @Override
    public List<T> find(String hql, PageInfo page, Object... params) {
        Query query = this.getSession().createQuery(hql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
        }

        if (page != null) {
            query.setFirstResult((page.getPage() - 1) * page.getRows()).setMaxResults(page.getRows());
        }

        List<T> list = query.list();
        List<T> result = new ArrayList<T>();
        for (T t : list) {
            result.add(this.converEntity(t, clazz));
        }
        return query.list();
    }

    @Override
    public List<T> findByExample(T entity) {
        return findByExample(entity, null, null);
    }

    @Override
    public List<T> findByExample(T entity, List<OrderInfo> orders, PageInfo page) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(clazz);
        criteria.add(Example.create(entity));

        if (orders != null) {
            for (OrderInfo order : orders) {
                if (OrderInfo.ORDER_TYPE_ASC.equals(order.getType())) {
                    criteria.addOrder(Order.asc(order.getProperty()));
                } else if (OrderInfo.ORDER_TYPE_DESC.equals(order.getType())) {
                    criteria.addOrder(Order.desc(order.getProperty()));
                }
            }
        }
        if (page != null) {
            criteria.setFirstResult((page.getPage() - 1) * page.getRows()).setMaxResults(page.getRows());
        }

        List<T> list = criteria.list();
        List<T> result = new ArrayList<T>();
        for (T t : list) {
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
        for (Serializable id : ids) {
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

    @Override
    public <ITEM extends BaseQueryItem> List<ITEM> findByNamedQuery(BaseQueryCondition condition, Class<ITEM> itemClass) {
        return findByNamedQuery(condition, itemClass,null);
    }

    @Override
    public <ITEM extends BaseQueryItem> List<ITEM> findByNamedQuery(BaseQueryCondition condition, Class<ITEM> itemClass,PageInfo page) {
        //获取condition类路径下的XXX.hbm.xml中描述的sql查询语句
        String sql = NamedQueryUtil.getDynamicSql(condition.getClass());
        //过滤查询条件
        sql = NamedQueryUtil.filterQueryCondition(sql, condition);
        System.out.println(sql);
        //创建会话
        Session session = this.getSession();
        //指定查询语句
        Query query = session.createSQLQuery(sql);
        //设置查询参数
        String[] params = query.getNamedParameters();
        if(params != null){
            for(String param : params){
                try {
                    //获取查询参数的类型
                    String type = ReflectUtil.getFieldType(condition, param);
                    Class clazz = this.getClass().getClassLoader().loadClass(type);
                    if(Collection.class.isAssignableFrom(clazz)){
                        query.setParameterList(param, (Collection)ReflectUtil.getFieldValue(condition, param));
                    }else{
                        query.setParameter(param,ReflectUtil.getFieldValue(condition, param));
                    }
                } catch (ClassNotFoundException e) {
                    logger.error(e.getMessage(),e);
                }
            }
        }
        //设置分页查询条件
        if (page != null) {
            query.setFirstResult((page.getPage() - 1) * page.getRows()).setMaxResults(page.getRows());
        }
        //指定查询结果以Map形式封装, Key表的字段名称
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        //执行查询
        List list = query.list();
        //封装结果集
        List<ITEM> itemList = new ArrayList<ITEM>();
        try {
            for (Object obj : list) {
                //封装一行记录
                ITEM item = itemClass.newInstance();
                //行结果集
                HashMap<String, Object> map = (HashMap<String, Object>) obj;
                Set<Entry<String, Object>> set = map.entrySet();
                Iterator<Entry<String, Object>> it = set.iterator();
                while (it.hasNext()) {
                    Entry<String, Object> entry = it.next();
                    //将字段名转换成对象的属性名
                    String column = entry.getKey();
                    Object value = entry.getValue();

                    String field = CamelCaseUtil.toCamelCase(column);
                    ReflectUtil.setFieldValue(item, field, value);
                }
                //添加行
                itemList.add(item);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return itemList;
    }

}