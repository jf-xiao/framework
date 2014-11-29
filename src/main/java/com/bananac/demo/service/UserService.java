package com.bananac.demo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.NamedSQLQueryDefinition;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import com.bananac.demo.dao.UsersDAO;
import com.bananac.demo.entity.Users;
import com.bananac.demo.query.SysModularQueryCondition;
import com.bananac.demo.query.SysModularQueryItem;
import com.bananac.framework.core.query.BaseQueryCondition;
import com.bananac.framework.core.query.BaseQueryItem;
import com.bananac.framework.core.query.NamedQueryUtil;
import com.bananac.framework.core.util.ReflectUtil;

@Service("userService")
public class UserService {
    @Resource(name = "userDao")
    private UsersDAO userDao;

    public int userCount() {
        return userDao.getAllUser().size();
    }

    public void save() {
        Users users1 = new Users();
        users1.setId(1);
        users1.setAge(11);
        users1.setNice_name("肖建锋3");

        Users users2 = new Users();
        users2.setAge(11);
        users2.setNice_name("肖建锋4");

        List list = new ArrayList();
        // list.add(users1);
        // list.add(users2);
        list.add(1);
        list.add(2);
        list.add(3);
        userDao.get(list);
    }

    public void test() throws IOException {
        String hql = NamedQueryUtil.getSql(SysModularQueryCondition.class);
        userDao.q(hql);

        /*
         * String[] arr = query.getNamedParameters(); for(String p : arr){
         * System.out.println(p+"------------------->"); }
         */
    }

    public void test2() {

        SysModularQueryCondition condition = new SysModularQueryCondition();
        //condition.setId("1");
        List<String> codes = new ArrayList<String>();
        codes.add("1");
        codes.add("2");
        condition.setCode(codes);
        userDao.findByNamedQuery(condition, SysModularQueryItem.class);
        // this.getHql2(condition, SysModularQueryItem.class);
        //String hql = NamedQueryUtil.getHql(condition.getClass());
        //System.out.println(hql);
    }

    public <Item extends BaseQueryItem> String getHql2(BaseQueryCondition condition, Class<Item> item) {
        NamedSQLQueryDefinition nsq;
        String classpath = ReflectUtil.getClasspath(condition.getClass());
        String name = ReflectUtil.getName(condition.getClass());
        try {
            String searchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                    + org.springframework.util.ClassUtils.convertClassNameToResourcePath(classpath) + "/"
                    + name.substring(0, name.lastIndexOf("Condition")) + ".hbm.xml";
            Configuration cfg = new Configuration();
            org.springframework.core.io.support.ResourcePatternResolver resourcePatternResolver = new org.springframework.core.io.support.PathMatchingResourcePatternResolver();
            org.springframework.core.io.Resource[] resources = resourcePatternResolver.getResources(searchPath);
            cfg.addInputStream(resources[0].getInputStream());
            cfg.buildMappings();
            Object obj = cfg.getNamedSQLQueries().get(name.substring(0, name.lastIndexOf("Condition")));
            nsq = (NamedSQLQueryDefinition) obj;

            return nsq.getQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getHql() throws IOException {
        String searchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                + org.springframework.util.ClassUtils.convertClassNameToResourcePath("com/bananac/**/")
                + "SysModular.hbm.xml";
        System.out.println(searchPath);
        Configuration cfg = new Configuration();
        org.springframework.core.io.support.ResourcePatternResolver resourcePatternResolver = new org.springframework.core.io.support.PathMatchingResourcePatternResolver();
        org.springframework.core.io.Resource[] resources = resourcePatternResolver.getResources(searchPath);
        cfg.addInputStream(resources[0].getInputStream());
        cfg.buildMappings();
        Object obj = cfg.getNamedSQLQueries().get("SysModularQuery");

        NamedSQLQueryDefinition nsq = (NamedSQLQueryDefinition) obj;
        System.out.println(nsq.getQuery());

        return nsq.getQuery();
    }

    /**
     * 
     * 2014-11-28
     */
    public void test3() {
        SysModularQueryCondition condition = new SysModularQueryCondition();
        //condition.setId("1");
        List<String> codes = new ArrayList<String>();
        codes.add("1");
        codes.add("2");
        condition.setCode(codes);
        String sql = NamedQueryUtil.getDynamicSql(condition.getClass());
        sql = NamedQueryUtil.filterQueryCondition(sql,condition);
    }

}