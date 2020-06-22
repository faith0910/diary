package com.shine.service;

import com.shine.entity.Class;

import java.util.List;

/**
 * @program:Shine
 * @description:
 * @author:Juwenchao
 * @date:2020-06-19 19:24:30
 */
public interface IclassService {

    Class getClass(Integer cid);

    List<Class> getAll();
}
