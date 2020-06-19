package com.nannan.service;/**
 * @author wuchunjie
 * @date 2020/5/26
 */

import com.nannan.pojo.User;

import java.util.List;

/**
 * @program:demo
 * @description:
 * @author:Juwenchao
 * @date:2020-05-26 11:39:40
 */
public interface UserService {

    int update(User user);

    int add(User user);

    List<User> getAll(int page,int rows);

    List<User> queryUserByName(String name);

    int del(Integer id);
}
