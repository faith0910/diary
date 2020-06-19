package com.nannan.service.impl;/**
 * @author wuchunjie
 * @date 2020/5/26
 */

import com.github.pagehelper.PageHelper;
import com.nannan.mapper.UserMapper;
import com.nannan.pojo.User;
import com.nannan.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program:demo
 * @description:
 * @author:Juwenchao
 * @date:2020-05-26 11:48:48
 */

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public int update(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public int add(User user) {
        return userMapper.insert(user);
    }

    @Override
    public List<User> getAll(int page, int rows) {

        PageHelper.startPage(page, rows);

        return userMapper.selectByExample(null);

    }

    @Override
    public List<User> queryUserByName(String name) {
        return this.userMapper.queryUserByName(name);
    }

    @Override
    public int del(Integer id) {

        return this.userMapper.deleteByPrimaryKey(id);
    }

}
