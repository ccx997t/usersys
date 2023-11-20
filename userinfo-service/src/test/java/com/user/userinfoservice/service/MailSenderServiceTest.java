package com.user.userinfoservice.service;

import com.user.userinfoservice.MailConfiguration;
import com.user.userinfoservice.UserinfoServiceApplication;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserinfoServiceApplication.class})
public class MailSenderServiceTest {

    @Autowired
    MailSenderService mailSenderService;
    @Test
    public void sendEmail()
    {
        mailSenderService.sendNewMail("ccx997t3@163.com","fffc63@163.com","welcome","welcome");
    }
}
