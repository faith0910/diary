package com.shine.service.impl;/**
 * @author wuchunjie
 * @date 2020/6/19
 */

import com.shine.entity.Class;
import com.shine.mapper.ClassMapper;
import com.shine.service.IclassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @program:Shine
 * @description:
 * @author:Juwenchao
 * @date:2020-06-19 19:26:46
 */

@Service
public class ClassServiceImpl implements IclassService {

    @Autowired
    ClassMapper classMapper;
    @Override
    public Class getClass(Integer cid) {
        return classMapper.getClass(cid);
    }

    @Override
    public List<Class> getAll() {
        return classMapper.selectAll();
    }
}
