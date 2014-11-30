package com.bananac.framework.core.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * xml文档解析工具类
 * 
 * @author xiaojf 294825811@qq.com 2014-11-28
 */
public class XmlParseUtil {

    private static XmlParseUtil parseUtil;
    private static SAXReader saxReadr;
    private static InputStream stream;
    private static Document document;
    
    private static Logger logger = LoggerFactory.getLogger(XmlParseUtil.class);

    private XmlParseUtil() {

    }

    public static XmlParseUtil getInstance(InputStream is) {
        if (parseUtil == null) {
            parseUtil = new XmlParseUtil();
        }
        saxReadr = new SAXReader();
        //忽略对DTD文件的检查,提高读取XML速度
        saxReadr.setEntityResolver(new EntityResolver() {

            @Override
            public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
                return new InputSource(new ByteArrayInputStream("<?xml version='1.0' encoding='UTF-8'?>".getBytes()));
            }
        });
        stream = is;
        try {
            document = saxReadr.read(stream);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return parseUtil;
    }

    public Element getRootElement() {
        return document.getRootElement();
    }

    public String getElementName(Element element) {
        if (element == null) {
            return "";
        }
        return element.getName();
    }

    public List<Element> getChildElement(String nodeName) {
        List<Element> list = new ArrayList<Element>();
        Element root = this.getRootElement();
        for (Iterator<Element> it = root.elementIterator(nodeName); it.hasNext();) {
            Element ele = it.next();
            list.add(ele);
        }

        return list;
    }

    public List<Attribute> getAttributes(Element element) {
        List<Attribute> attributes = new ArrayList<Attribute>();
        for (Iterator<Attribute> it = element.attributeIterator(); it.hasNext();) {
            Attribute attribute = it.next();
            attributes.add(attribute);
        }

        return attributes;
    }
}
