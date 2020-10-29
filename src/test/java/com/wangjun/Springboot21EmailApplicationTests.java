package com.wangjun;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;

@SpringBootTest
@RunWith(SpringRunner.class)
class Springboot21EmailApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private JavaMailSenderImpl mailSender;

    /**
     * 发送包含简单文本邮件
     */
    @Test
    public void sendTxtMail(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        //设置收件人
        simpleMailMessage.setTo(new String[]{"1617171230@qq.com","15639655621@163.com"});
        //设置寄件人
        simpleMailMessage.setFrom("forward_xn@163.com");
        //邮件标题
        simpleMailMessage.setSubject("SpringBoot 测试邮件发送【文本】");
        //邮件内容
        simpleMailMessage.setText("主体内容：晚上好，早些休息！");
        //发送
        mailSender.send(simpleMailMessage);
        System.out.println("邮件已发送！");
    }

    /**
     * 发送包含HTML文本的邮件
     * @throws Exception
     */
    @Test
    public void sendHtmlMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo("1617171230@qq.com");
        mimeMessageHelper.setFrom("forward_xn@163.com");
        mimeMessageHelper.setSubject("Spring Boot Mail 邮件测试【HTML】");

        StringBuilder sb = new StringBuilder();
        sb.append("<html><head></head>");
        sb.append("<body>" +
                "<h1>springBoot 邮件测试</h1>" +
                "<p>" +
                "hello! this is spring mail test。" +
                "</p>" +
                "<a href='http://www.baidu.com'>百度一下吧</a>" +
                "</body>");
        sb.append("</html>");

        // 启用html
        mimeMessageHelper.setText(sb.toString(), true);
        // 发送邮件
        mailSender.send(mimeMessage);

        System.out.println("邮件已发送");
    }


}
