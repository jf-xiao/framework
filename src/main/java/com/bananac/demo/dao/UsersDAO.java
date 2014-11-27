package com.bananac.demo.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.bananac.demo.entity.Users;
import com.bananac.framework.core.dao.impl.BaseDaoImpl;

@Repository("userDao")
public class UsersDAO extends BaseDaoImpl<Users>{

    public List<Users> getAllUser(){
        String hsql="from SysModular";
        return this.find(hsql);
    }
    
    public void q(String hql){
        Session session = this.getSession();
        Query query = session.createSQLQuery(hql);
        /*String[] ps = query.getNamedParameters();
        for (int i = 0; i < ps.length; i++) {
            System.out.println(ps[i]+"----------------->");
        }*/
        
        String sql = query.getQueryString();
        System.out.println(sql);

        
      //指定查询结果以Map形式封装, Key表的字段名称
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        
        List list = query.list();
        
        for(Object obj : list){
           HashMap<String, Object> map = (HashMap<String, Object>) obj;
           Set<Entry<String, Object>> set = map.entrySet();
           Iterator<Entry<String, Object>> it = set.iterator();
           while(it.hasNext()){
               Entry<String, Object> entry = it.next();
               System.out.println(entry.getKey()+"----------->"+entry.getValue());
           }
        }
    }
}