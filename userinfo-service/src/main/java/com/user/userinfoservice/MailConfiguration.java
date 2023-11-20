package com.user.userinfoservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfiguration {

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.163.com");
        mailSender.setPort(465);

        mailSender.setUsername("ccx997t3@163.com");
        mailSender.setPassword("XWCALTXIHRAGJEHT");


        Properties props = mailSender.getJavaMailProperties();
   //     props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.debug", "true");


       props.setProperty("mail.smtp.auth", "true");
//        props.setProperty("mail.smtp.port", "465");
//        props.setProperty("mail.smtp.socketFactory.port", "465");
       props.put("mail.smtp.starttls.enable", "true");
       props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
      props.put("mail.debug", "true");
        return mailSender;
    }
}




