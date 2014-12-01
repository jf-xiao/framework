package com.bananac.framework.email;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.util.StringUtils;

/**
 * 邮件发送
 * @author xiaojf 294825811@qq.com 2014-12-1
 */
@Component("emailSender")
public class EmailSenderImpl implements EmailSender {
    @Resource(name="mailSender")
    private JavaMailSenderImpl mailSender;
    @Resource(name="velocityEngine")
    private VelocityEngine velocityEngine;
    
    public void sendSimpleEmail(String subject,String from ,String[] to,String text) {

        // 建立邮件消息
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        // 设置收件人，寄件人 用数组发送多个邮件
        mailMessage.setTo(to);
        mailMessage.setFrom(from);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);

        // 发送邮件
        mailSender.send(mailMessage);
    }

    public void sendHtmlEmail(String subject,String from ,String[] to,String html) throws MessagingException {

        // 建立邮件消息,发送简单邮件和html邮件的区别
        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage);

        // 设置收件人，寄件人
        messageHelper.setTo(to);
        messageHelper.setFrom(from);
        messageHelper.setSubject(subject);
        // true 表示启动HTML格式的邮件
        messageHelper.setText(html, true);

        // 发送邮件
        mailSender.send(mailMessage);
    }

    public void sendImageEmail(String subject,String from ,String[] to,String html,Map<String, String> map) throws MessagingException {

        // 建立邮件消息,发送简单邮件和html邮件的区别
        MimeMessage mailMessage = mailSender.createMimeMessage();
        // 注意这里的boolean,等于真的时候才能嵌套图片，在构建MimeMessageHelper时候，所给定的值是true表示启用，
        // multipart模式
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true);

        // 设置收件人，寄件人
        messageHelper.setTo(to);
        messageHelper.setFrom(from);
        messageHelper.setSubject(subject);
        // true 表示启动HTML格式的邮件
        messageHelper.setText(html, true);

        Set<Map.Entry<String, String>> set = map.entrySet();
        for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
            //图片key
            String key = entry.getKey();
            //附件地址
            String imgPath = entry.getValue();
            if (!StringUtils.isEmpty(key)) {
                //读取图片
                FileSystemResource img = new FileSystemResource(new File(imgPath));
                messageHelper.addInline(key, img);
            }
        }

        // 发送邮件
        mailSender.send(mailMessage);
    }

    public void sendAttachedFileMail(String subject,String from ,String[] to,String html,String[] attaches) throws MessagingException {

        // 建立邮件消息,发送简单邮件和html邮件的区别
        MimeMessage mailMessage = mailSender.createMimeMessage();
        // 注意这里的boolean,等于真的时候才能嵌套图片，在构建MimeMessageHelper时候，所给定的值是true表示启用，
        // multipart模式 为true时发送附件 可以设置html格式
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");
        //收件人
        messageHelper.setTo(to);
        //发件人
        messageHelper.setFrom(from);
        //标题
        messageHelper.setSubject(subject);
        //true 表示启动HTML格式的邮件
        messageHelper.setText(html, true);
        
        for(String attache : attaches){
            FileSystemResource file = new FileSystemResource(new File(attache));
            // 这里的方法调用和插入图片是不同的。
            messageHelper.addAttachment(attache.substring(attache.lastIndexOf("/")), file);
        }

        // 发送邮件
        mailSender.send(mailMessage);
    }
    
    public void sendEmail(final String[] to, final String from, final String subject, final String template,
            final Map<String, Object> model, final Map<String, String> attachmentMap) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
                // 收件人
                message.setTo(to);
                // 发件人
                message.setFrom(from);
                // 标题
                message.setSubject(subject);
                // 正文
                String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template, "UTF-8", model);
                message.setText(body, true);
                // 添加附件
                Set<Map.Entry<String, String>> set = attachmentMap.entrySet();
                for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
                    Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
                    // 附件名称
                    String attachmentName = entry.getKey();
                    // 附件地址
                    String attachmentPath = entry.getValue();
                    if (!StringUtils.isEmpty(attachmentPath)) {
                        // 读取附件
                        FileSystemResource file = new FileSystemResource(attachmentPath);
                        // 添加附件
                        message.addAttachment(attachmentName, file);
                    }
                }
            }
        };
        mailSender.send(preparator);
    }

}
