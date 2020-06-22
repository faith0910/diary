package com.shine.mapper;/**
 * @author wuchunjie
 * @date 2020/6/19
 */

import com.shine.entity.Stu;

import java.util.List;

/**
 * @program:Shine
 * @description:
 * @author:Juwenchao
 * @date:2020-06-19 21:04:43
 */
public interface StuMapper {
    int deleteByPrimaryKey(Integer tid);

    int insert(Stu record);

    Stu selectByPrimaryKey(Integer tid);

    List<Stu> selectAll();

    int updateByPrimaryKey(Stu record);
}
