/**
 * 
 */
package com.bananac.framework.email;

import java.util.Map;

import javax.mail.MessagingException;

/**
 * @author xiaojf 294825811@qq.com
 * 2014-12-1
 */
public interface EmailSender {
    /**
     * 发送普通文本邮件[群发]
     * @param subject 邮件标题
     * @param from 发送人邮件地址
     * @param to 多个收件人邮件地址
     * @param text 邮件内容
     * 2014-12-1
     */
    public void sendSimpleEmail(String subject,String from ,String[] to,String text);
    /**
     * 发送HTML邮件
     * @param subject 标题
     * @param from 发送人邮件地址
     * @param to 收件人邮件地址
     * @param html HTML邮件内容
     * @throws MessagingException
     * 2014-12-1
     */
    public void sendHtmlEmail(String subject,String from ,String[] to,String html) throws MessagingException;
    /**
     * 发生正文含有图片的邮件
     * 
     * @param subject 标题
     * @param from 发件人
     * @param to 收件人
     * @param html 正文
     * @param map key为正文中图片的标识， value 为图片地址
     * @throws MessagingException
     * 2014-12-1<br/>
     * JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();<br/>
     * senderImpl.createMimeMessage();<br/>
     * messageHelper = new MimeMessageHelper(mailMessage,true);<br/>
     * 
     * messageHelper.setFrom("username@163.com");<br/>
     * messageHelper.setSubject("测试邮件中嵌套图片!！");<br/>
     * messageHelper.setText("<html><head></head><body><h1>hello!!spring image html mail<img src=\"cid:aaa\"/></body></html>",true);<br/>
     * FileSystemResource img = new FileSystemResource(new File("g:/123.jpg"));<br/>
     * 
     * messageHelper.addInline("aaa",img);<br/>
     * 
     * senderImpl.setUsername("username") ; <br/>
     * senderImpl.setPassword("password") ;<br/>
     * Properties prop = new Properties() ;<br/>
     * prop.put("mail.smtp.auth", "true") ;<br/>
     * prop.put("mail.smtp.timeout", "25000") ;<br/>
     * senderImpl.setJavaMailProperties(prop);<br/>
     * System.out.println("邮件发送成功..");
     */
    public void sendImageEmail(String subject,String from ,String[] to,String html,Map<String, String> map) throws MessagingException;
    /**
     * 
     * @param subject 标题
     * @param from 发件人邮件地址
     * @param to 多个收件人邮件地址
     * @param html html邮件内容
     * @param attaches 附件
     * @throws MessagingException
     * 2014-12-1
     */
    public void sendAttachedFileMail(String subject,String from ,String[] to,String html,String[] attaches) throws MessagingException ;
    /**
     * 发送邮件
     * @param to 收件人右键地址
     * @param from 发件人地址
     * @param subject 标题
     * @param template 邮件内容模板地址
     * @param model 邮件内容模板参数对象
     * @param attachmentMap 附件map, 以附件名称为key ， 附件地址为value
     * 2014-12-1
     */
    public void sendEmail(final String[] to, final String from, final String subject, final String template,final Map<String, Object> model, final Map<String, String> attachmentMap);
}
