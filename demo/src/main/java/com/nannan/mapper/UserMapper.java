package com.nannan.mapper;/**
 * @author wuchunjie
 * @date 2020/5/26
 */

import com.github.abel533.mapper.Mapper;
import com.nannan.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program:demo
 * @description:
 * @author:Juwenchao
 * @date:2020-05-26 11:11:25
 */
public interface UserMapper extends Mapper<User> {



    @Select("select * from user where user_name like '%${value}%'")

     List<User> queryUserByName(String name);






}
