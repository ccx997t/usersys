package com.user.userinfoservice.dao;

import com.user.userinfoservice.UserinfoServiceApplication;
import com.user.userinfoservice.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@SpringBootTest(classes = {UserinfoServiceApplication.class})
public class UserDaoTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public  void insert()
    {

        User user=new User();
        user.setName("mmmnnn2");
        userMapper.insert(user);
        Assert.assertEquals(user.getName(),"mmmnnn2");
    }

    @Test
    public  void update()
    {

        User user=new User();
        user.setId(12);
        user.setEmail("xx");
        int count=userMapper.updateByPrimaryKey(user);
        Assert.assertEquals(count,1);
    }

    @Test
    public  void deleteBatch()
    {

        List list=new ArrayList<Long>();
        list.add(11);
        list.add(12);
       int count= userMapper.updateBatch(list);
        Assert.assertEquals(count,2);
    }
    @Test
    public  void read()
    {
        User user= userMapper.selectByPrimaryKey(12);
        Assert.assertNotNull(user);
    }

    @Test
    public  void selectUserByName()
    {

        HashMap<String,String> map=new HashMap<>();
        map.put("name","mmm");
        List<User> list=userMapper.selectUsers( map);
        Assert.assertEquals(list.size(),1);
    }

    @Test
    public  void selectUserByEmail()
    {

        HashMap<String,String> map=new HashMap<>();
        map.put("email","ccx@163.com");
        List<User> list=userMapper.selectUsers( map);
        Assert.assertEquals(list.size(),1);
    }
}
