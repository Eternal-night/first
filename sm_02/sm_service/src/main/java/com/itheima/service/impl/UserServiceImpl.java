package com.itheima.service.impl;

import com.itheima.domain.User;
import com.itheima.mapper.UserMapper;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public boolean findLogin(User user) throws Exception {
        User u = userMapper.findLogin(user);
        boolean flag=false;
        if(u!=null){
            flag=true;
        }
        return flag;
    }

    public List<User> findAll(Integer pageNum,Integer pageSize,User user) throws Exception{
        return userMapper.findAll((pageNum-1)*pageSize,pageSize,user);
    }

    public void saveUser(User user)throws Exception {
        userMapper.saveUser(user);
    }

    public User findById(int id) {
        return userMapper.findById(id);
    }

    public int findTotal(User user) {
        return userMapper.findTotal(user);
    }
}
