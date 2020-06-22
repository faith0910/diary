package com.shine.demo.mapper;

import com.shine.demo.entity.Stu;
import java.util.List;

public interface StuMapper {
    int deleteByPrimaryKey(Integer sId);

    int insert(Stu record);

    Stu selectByPrimaryKey(Integer sId);

    List<Stu> selectAll();

    int updateByPrimaryKey(Stu record);
}