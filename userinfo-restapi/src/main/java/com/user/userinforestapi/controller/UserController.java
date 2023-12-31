package com.user.userinforestapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.usercommon.model.DeleteUser;
import com.user.usercommon.model.Email;
import com.user.usercommon.model.User;
import com.user.usercommon.web.RestResult;
import com.user.usercommon.web.ResultCode;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    RestTemplate restTemplate;
    private final String title="usersys-welcome email";
    private final String content="you are welcome";
    private final String emailregex="^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";

    @ApiOperation("get userinfo by userid")
    @ApiImplicitParam(name = "id",value = "userid")
    @GetMapping("get/{id}")
    public  RestResult<User> getUserById(@PathVariable Long id) {
        try
        {
            String res=restTemplate.getForEntity("http://USERSERVICE/user/get/"+id,String.class).getBody();
            RestResult result = new ObjectMapper().readValue(res, RestResult.class);
            return result;
        }
        catch (Exception e)
        {
            return RestResult.fail(ResultCode.Server_ERROR);
        }
    }

    @ApiOperation("register new user and send email")
    @PostMapping("addNewUser")
    public  RestResult<Object> addNewUser(@RequestBody User user) {
        try
        {
           if (!checkEmail(user.getEmail()))
            {
                return RestResult.successNoData(ResultCode.PARAM_IS_INVALID);
            }
            //check the user name or email
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            headers.setContentType(type);
            HttpEntity httpEntity = new HttpEntity<>(user, headers);
            RestResult checkres=restTemplate.postForObject("http://USERSERVICE/user/searchNameorEmail",httpEntity, RestResult.class);
            Boolean issame = new ObjectMapper().convertValue(checkres.getData(),Boolean.class);
            if(issame==true)
            {
                return RestResult.successNoData(ResultCode.USER_NAME_EXISTS);
            }

            if(Strings.isNotBlank(user.getPassword()))
            {
                String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
                user.setPassword(md5Password);
            }
            RestResult res=restTemplate.postForObject("http://USERSERVICE/user/insert/",httpEntity, RestResult.class);
            User user1 = new ObjectMapper().convertValue(res.getData(),User.class);
            // send email
            Email email=new Email();
            email.setAddress(user.getEmail());
            email.setTitle(title);
            email.setContent(content);
            HttpEntity httpEntity1 = new HttpEntity<>(email, headers);
            RestResult emailres=restTemplate.postForObject("http://USERSERVICE/user/sendemail",httpEntity1, RestResult.class);
            //When the email is sent successfully update user status
           if(emailres.getCode()== ResultCode.SUCCESS.getCode())
           {
               User user2=new User();
               user2.setId(user1.getId());
               user2.setIsSend(1);
               HttpEntity httpEntity2 = new HttpEntity<>(user2, headers);
               RestResult result=restTemplate.postForObject("http://USERSERVICE/user/update",httpEntity2, RestResult.class);
               return result;
           }
           return emailres;

        }
        catch (Exception e)
        {
            return RestResult.fail(ResultCode.Server_ERROR);
        }
    }
    public  boolean checkEmail(String email){
        if(StringUtils.isNotBlank(email))
        {
            return email.matches(emailregex);
        }
        return false;

    }
    @ApiOperation("delete one or many userinfo by userids")
    @PostMapping("delete")
    @ResponseBody
    public RestResult<Object> deleteUserInfoByIds(@RequestBody DeleteUser user) {
        try {
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            headers.setContentType(type);
            HttpEntity httpEntity = new HttpEntity<>(user, headers);
            RestResult res=restTemplate.postForObject("http://USERSERVICE/user/delete",httpEntity, RestResult.class);
            return res;

        } catch (Exception e) {
            return RestResult.fail(ResultCode.Server_ERROR);
        }
    }

    @ApiOperation("update user info")
    @PostMapping("update")
    @ResponseBody
    public RestResult<Object> update(@RequestBody User user) {
        try {
            if(Strings.isNotBlank(user.getPassword()))
            {
                String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
                user.setPassword(md5Password);
            }
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            headers.setContentType(type);
            HttpEntity httpEntity = new HttpEntity<>(user, headers);
            RestResult res=restTemplate.postForObject("http://USERSERVICE/user/update",httpEntity, RestResult.class);
            return res;

        } catch (Exception e) {
            return RestResult.fail(ResultCode.Server_ERROR);
        }
    }

    @PostMapping("sendEmail")
    public  RestResult<Object> sendEmail(@RequestBody Email email) {
        try
        {
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            headers.setContentType(type);
            HttpEntity httpEntity = new HttpEntity<>(email, headers);
            RestResult res=restTemplate.postForObject("http://USERSERVICE/user/sendemail/",httpEntity, RestResult.class);
            // RestResult result = new ObjectMapper().readValue(res, RestResult.class);
            return res;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return RestResult.fail(ResultCode.Server_ERROR);
        }
    }
}
