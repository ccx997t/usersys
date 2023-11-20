package com.user.userinfoservice.dao;


import com.user.userinfoservice.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    User  selectByPrimaryKey(Integer id);

    int insert(User record);

    int updateByPrimaryKey(User record);

    int updateBatch(List<Integer> list);

    List<User>  selectUsers(Map map);
}
