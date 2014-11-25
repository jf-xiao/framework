/**
 * 
 */
package com.bananac.demo.entity;

import java.io.IOException;

import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.NamedSQLQueryDefinition;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * @author xiaojf 294825811@qq.com
 * 2014-11-25
 */
public class Test {

    /**
     * @param args
     * 2014-11-25
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String searchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                org.springframework.util.ClassUtils.convertClassNameToResourcePath("com/bananac/**/")+"UsersQuery.hbm.xml";
        System.out.println(searchPath);
        Configuration cfg = new Configuration();
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resourcePatternResolver.getResources(searchPath);
        cfg.addInputStream(resources[0].getInputStream());
        cfg.buildMappings();
        Object obj = cfg.getNamedSQLQueries().get("UsersQuery");
        NamedSQLQueryDefinition nsq = (NamedSQLQueryDefinition) obj;
        System.out.println(nsq.getQuery());
    }

}
