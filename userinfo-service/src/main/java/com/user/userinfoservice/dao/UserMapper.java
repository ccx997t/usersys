package com.user.userinfoservice.dao;

import com.example.mybaitis.model.TbUser;
import com.user.userinfoservice.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    @Update("<script> update tb_user set is_delete=1 where  id in <foreach collection=\"list\" item=\"item\" open=\"(\" separator=\",\" close=\")\">" +
            "#{item}" +
            "</foreach> </script>")
    int deleteUser(@Param("list") List<Long> list);

    User  selectByPrimaryKey(Integer id);

    int insert(User record);

    int updateByPrimaryKey(User record);

    int updateBatch(List<Integer> list);

    List<User>  selectUsers(Map map);
}
