package com.bananac.framework.email.test;

import static org.junit.Assert.*;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bananac.framework.email.EmailSenderImpl;

/**
 * @author xiaojf 294825811@qq.com
 * 2014-12-1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-email.xml")
public class EmailSenderImplTest {
    @Resource(name="emailSender")
    private EmailSenderImpl emailSender;

    public void testSendSimpleEmail() {
        emailSender.sendSimpleEmail("测试","294825811@qq.com",new String[]{"294825811@qq.com"},"测试我的简单邮件发送机制！ ");
    }

    public void testSendHtmlEmail() throws MessagingException {
        emailSender.sendHtmlEmail("测试发送html邮件", "294825811@qq.com", new String[]{"872332133@qq.com"}, "<html><head></head><body><h1>hello!!spring image html mail</h1>/></body></html>");
    }

}
