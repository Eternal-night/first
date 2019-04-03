package com.itheima.mapper;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    User findLogin(User user) throws Exception;

    //查询所有，带分页

    List<User> findAll(@Param("pageStart") Integer pageStart, @Param("pageSize") Integer pageSize,@Param("user") User user) throws Exception;

    void saveUser(User user) throws Exception;

    User findById(int id);

    int findTotal(User user);
}
