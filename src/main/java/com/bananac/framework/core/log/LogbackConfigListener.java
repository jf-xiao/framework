package com.bananac.framework.core.log;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

/**
 * Logback 监听器, 用于项目启动后加载Logback日志框架
 * 
 * @author xiaojf 294825811@qq.com 2014-11-30
 */
public class LogbackConfigListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(LogbackConfigListener.class);
    private static final String CONFIG_LOCATION = "logbackConfigLocation";

    @Override
    public void contextInitialized(ServletContextEvent event) {
        //从web.xml中加载指定文件名的日志配置文件
        String logbackConfigLocation = event.getServletContext().getInitParameter(CONFIG_LOCATION);
        String fn = event.getServletContext().getRealPath(logbackConfigLocation);
        try {
            LoggerContext loggerContext = (LoggerContext)LoggerFactory.getILoggerFactory();
            loggerContext.reset();
            JoranConfigurator joranConfigurator = new JoranConfigurator();
            joranConfigurator.setContext(loggerContext);
            joranConfigurator.doConfigure(fn);
            logger.debug("loaded slf4j configure file from {}", fn);
        }
        catch (JoranException e) {
            logger.error("can loading slf4j configure file from " + fn, e);
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

    }

}
