package com.nannan.controller;

import com.nannan.enums.Code;
import com.nannan.pojo.User;
import com.nannan.response.Result;
import com.nannan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program:demo
 * @description:
 * @author:Juwenchao
 * @date:2020-05-26 11:52:18
 */


@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 查看列表并分页
     */
    @RequestMapping("userList/{page}/{rows}")
    public Result queryUserAll(@PathVariable Integer page, @PathVariable Integer rows) {
        List<User> list = userService.getAll(page, rows);
        if (!list.isEmpty()) {
            return Result.suc(list);
        }
        return Result.fail(Code.SYSTEM_ERROR);
    }

    /**
     * 根据名称查询
     */
    @RequestMapping("list/{name}")
    public Result queryUserByName(@PathVariable String name) {
        List<User> list = this.userService.queryUserByName(name);
        if (!list.isEmpty()) {
            return Result.suc(list);
        }
        return Result.fail(Code.USER_NOT_FIND);
    }

    /**
     * 注册用户
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result reg(@RequestBody User user) {
        Integer code = this.userService.add(user);
        if (!code.equals(Code.SUCCESS.code())) {
            return Result.fail(Code.USER_HAS_EXISTED);
        }
        return Result.suc();
    }

    /**
     * 更新用户
     */
    @RequestMapping("/update")
    public Result updateUser(@RequestBody User user) {
        Integer code = this.userService.update(user);
        if (!code.equals(Code.SUCCESS.code())) {
            return Result.fail(Code.SYSTEM_ERROR);
        }
        return Result.suc();
    }

    /**
     * 删除用户
     */
    @RequestMapping("/del/{id}")
    public Result delUser(@PathVariable Integer id) {
        Integer code = this.userService.del(id);
        if (!code.equals(Code.SUCCESS.code())) {
            return Result.fail(Code.SYSTEM_ERROR);
        }
        return Result.suc();
    }
}
