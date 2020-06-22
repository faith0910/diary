package com.shine.controller;/**
 * @author wuchunjie
 * @date 2020/6/19
 */

import com.shine.entity.Class;
import com.shine.response.Result;
import com.shine.service.IclassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program:Shine
 * @description:
 * @author:Juwenchao
 * @date:2020-06-19 19:28:39
 */

@RestController
@RequestMapping("api")
public class ClassController {
    @Autowired
    IclassService classService;

    @RequestMapping("/show/{cid}")
    public Result show(@PathVariable Integer cid) {
        Class  list = classService.getClass(cid);
//        System.out.println(list.getStudents());
        return Result.suc(list);
    }
    @RequestMapping("/showAll")
    public Result showAll() {
        List<Class> list = classService.getAll();
        return Result.suc(list);
    }
}