package com.bananac.framework.core.query;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.NamedSQLQueryDefinition;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.bananac.framework.core.util.ReflectUtil;

/**
 * NamedQuery工具类
 * @author xiaojf 294825811@qq.com 2014-11-27
 */
public class NamedQueryUtil {

    private static Map<String, String> queryStringMap = new HashMap<String, String>();

    /**
     * 获取在XXX.hbm.xml文件中定义的查询语句
     * @param condition 查询条件Class
     * @return 查询语句
     */
    public static <Condition extends BaseQueryCondition> String getSql(Class<Condition> condition) {
        NamedSQLQueryDefinition nsq;
        String classpath = ReflectUtil.getClasspath(condition);
        String name = ReflectUtil.getName(condition);
        String queryName = name.substring(0, name.lastIndexOf("Condition"));
        
        if(queryStringMap.get(queryName) == null){//如果是首次加载
            try {
                String searchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                        + org.springframework.util.ClassUtils.convertClassNameToResourcePath(classpath) + "/"
                        + name.substring(0, name.lastIndexOf("Condition")) + ".hbm.xml";
                Configuration cfg = new Configuration();
                org.springframework.core.io.support.ResourcePatternResolver resourcePatternResolver = new org.springframework.core.io.support.PathMatchingResourcePatternResolver();
                org.springframework.core.io.Resource[] resources = resourcePatternResolver.getResources(searchPath);
                cfg.addInputStream(resources[0].getInputStream());
                cfg.buildMappings();
                Object obj = cfg.getNamedSQLQueries().get(queryName);
                nsq = (NamedSQLQueryDefinition) obj;
                String queryStr = nsq.getQuery();
                
                queryStringMap.put(queryName, queryStr);
                
                return queryStr;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return queryStringMap.get(queryName);
        
    }
}
