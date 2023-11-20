package com.user.userinfoservice.controller;

import com.user.usercommon.model.DeleteUser;
import com.user.usercommon.model.Email;
import com.user.usercommon.web.RestResult;
import com.user.usercommon.web.ResultCode;
import com.user.userinfoservice.dao.UserMapper;
import com.user.userinfoservice.model.User;
import com.user.userinfoservice.service.MailSenderService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    MailSenderService mailService;

    @Value("${fromEmail}")
    private String fromEmail;


    @ApiOperation("get userinfo by userid")
    @ApiImplicitParam(name = "id",value = "userid")
    @GetMapping("get/{id}")
    public RestResult<User> getUserInfo(@PathVariable Integer id) {
        try {
            User user=  userMapper.selectByPrimaryKey(id);
            return RestResult.success(user, ResultCode.SUCCESS);
        } catch (Exception e) {
            return RestResult.fail(ResultCode.DATA_ACCESS_ERROR);
        }
    }


    @ApiOperation("delete one or many userinfo by userids, such as 1,2,3,  use separator,")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "user ids", paramType = "query", dataType = "string")
    })
    @PostMapping("delete")
    @ResponseBody
    public RestResult<User> deleteUserInfoByIds(@RequestBody DeleteUser user) {
        try {
            if(StringUtils.isNotBlank(user.getIds()))
            {
                String ids=user.getIds();
               String[]  idsArray=ids.split(",");
                List<Integer> list= new ArrayList<>();
                for (int i = 0; i <idsArray.length ; i++) {
                    list.add(Integer.parseInt(idsArray[i]));
                }
                userMapper.updateBatch(list);
                return RestResult.success(null, ResultCode.SUCCESS);
            }
            return RestResult.success(null, ResultCode.PARAM_IS_INVALID);

        } catch (Exception e) {
            return RestResult.fail(ResultCode.Server_ERROR);
        }
    }

    @ApiOperation("update user info")
    @PostMapping("update")
    @ResponseBody
    public RestResult<User> updateUser(@RequestBody User user)
    {
        try {
            userMapper.updateByPrimaryKey(user);
            return RestResult.success(null, ResultCode.SUCCESS);
        }catch (Exception e) {
            return RestResult.fail(ResultCode.Server_ERROR);
        }
    }
    @ApiOperation("add new user")
    @PostMapping("insert")
    @ResponseBody
    public RestResult<User> insertUser(@RequestBody User user)
    {
        try {
            userMapper.insert(user);
            return RestResult.success(user, ResultCode.SUCCESS);
        }catch (Exception e) {
            return RestResult.fail(ResultCode.Server_ERROR);
        }
    }
    @ApiOperation("Send mail to new users")
    @PostMapping("sendemail")
    @ResponseBody
    public RestResult<User> sendEmail(@RequestBody Email email)
    {
        String address=email.getAddress();
        String title=email.getTitle();
        String content=email.getContent();
        if(StringUtils.isBlank(address)||StringUtils.isBlank(title)||
                StringUtils.isBlank(content))
        {
             return RestResult.fail(ResultCode.PARAM_IS_INVALID);
        }
        try {
            mailService.sendNewMail(fromEmail,address,title,content);
            return RestResult.successNoData(ResultCode.SUCCESS);
        }catch (Exception e) {
            return RestResult.fail(ResultCode.Server_ERROR);
        }
    }
    @ApiOperation("Query the user name or email in the database and return whether there is the same")
    @PostMapping("searchNameorEmail")
    @ResponseBody
    public RestResult<Boolean> searchNameorEmail(@RequestBody Map<String,String> map)
    {
        try {
           List<User> list=userMapper.selectUsers(map);
           if(list!=null&&list.size()==1)
           {
               return RestResult.success(true, ResultCode.SUCCESS);
           }
           else{
               return RestResult.success(false, ResultCode.SUCCESS);
           }

        }catch (Exception e) {
            return RestResult.fail(ResultCode.Server_ERROR);
        }
    }
}
