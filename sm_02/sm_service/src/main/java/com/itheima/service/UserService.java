package com.itheima.service;

import com.itheima.domain.User;

import java.util.List;

public interface UserService {
    boolean findLogin(User user) throws Exception;

    List<User> findAll(Integer pageNum,Integer pageSize,User user) throws Exception;

    void saveUser(User user) throws Exception;

    User findById(int id);

    int findTotal(User user);
}
