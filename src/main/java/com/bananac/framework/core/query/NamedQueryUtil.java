package com.bananac.framework.core.query;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Element;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.NamedSQLQueryDefinition;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.bananac.framework.core.util.ReflectUtil;
import com.bananac.framework.core.util.XmlParseUtil;

/**
 * NamedQuery工具类
 * 
 * @author xiaojf 294825811@qq.com 2014-11-27
 */
public class NamedQueryUtil {

    private static Logger logger = Logger.getLogger(NamedQueryUtil.class);

    private static Map<String, String> queryStringMap = new HashMap<String, String>();//查询缓存

    /**
     * 根据condition的名称和所在的路径,动态读取hbm.xml中定义的查询条件,如
     * com.bananac.demo.query.SysModularQueryCondition 将会自动加载类路径
     * com.bananac.demo.query下的SysModularQuery.hbm.xml 中定义的查询语句name =
     * "SysModularQuery"
     * 
     * @param condition
     *            查询条件
     * @return 查询语句 2014-11-29
     */
    public static <Condition extends BaseQueryCondition> String getSql(Class<Condition> condition) {
        NamedSQLQueryDefinition nsq;
        String classpath = ReflectUtil.getClasspath(condition);
        String name = ReflectUtil.getName(condition);
        String queryName = name.substring(0, name.lastIndexOf("Condition"));

        if (queryStringMap.get(queryName) == null) {// 如果是首次加载
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
                logger.error(e.getMessage(), e);
            }
        }
        return queryStringMap.get(queryName);

    }

    /**
     * 根据condition的名称和所在的路径,动态读取hbm.xml中定义的查询条件,如
     * com.bananac.demo.query.SysModularQueryCondition 将会自动加载类路径
     * com.bananac.demo.query下的SysModularQuery.hbm.xml 中定义的查询语句name =
     * "SysModularQuery"
     * 
     * @param condition
     *            查询条件
     * @return 查询语句 2014-11-29
     */
    public static <Condition extends BaseQueryCondition> String getDynamicSql(Class<Condition> condition) {

        String text = null;
        String classpath = ReflectUtil.getClasspath(condition);
        String name = ReflectUtil.getName(condition);
        String queryName = name.substring(0, name.lastIndexOf("Condition"));
        if (queryStringMap.get(queryName) == null) {// 如果是首次加载
            try {
                String searchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                        + org.springframework.util.ClassUtils.convertClassNameToResourcePath(classpath) + "/"
                        + name.substring(0, name.lastIndexOf("Condition")) + ".hbm.xml";
                org.springframework.core.io.support.ResourcePatternResolver resourcePatternResolver = new org.springframework.core.io.support.PathMatchingResourcePatternResolver();
                org.springframework.core.io.Resource[] resources = resourcePatternResolver.getResources(searchPath);
                InputStream is = resources[0].getInputStream();
                XmlParseUtil parseUtil = XmlParseUtil.getInstance(is);
                List<Element> children = parseUtil.getChildElement("sql-query");
                Element child = children.get(0);
                text = child.getText().trim();
                
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }else{//直接获取已加载的查询语句
            text = queryStringMap.get(queryName);
        }
        return text;
    }

    /**
     * 根据查询条件值过滤查询语句中不需要加入的查询条件
     * 
     * @param sql
     *            查询语句
     * @param condition
     *            查询条件
     * @return 2014-11-29
     */
    public static String filterQueryCondition(String sql, BaseQueryCondition condition) {
        String newSql = sql;
        List<String> fieldNames = ReflectUtil.getAllFieldNames(condition.getClass());
        for (String fieldName : fieldNames) {
            // 获取查询条件值
            Object obj = ReflectUtil.getFieldValue(condition, fieldName);
            // Null值的查询条件不加入查询语句中
            if (obj == null) {
                newSql = newSql.replaceAll("<<.*:" + fieldName + ".*>>", "");
            }
        }

        // 替换sql中的<< >> 以及空空字符 , 如空格, 换行, 制表符等
        newSql = newSql.replaceAll("[<< | >>]+", " ").replaceAll("\\s+", " ").trim();

        return newSql;
    }

}
