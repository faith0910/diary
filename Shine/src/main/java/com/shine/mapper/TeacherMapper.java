package com.shine.mapper;

import com.shine.entity.Teacher;
import java.util.List;

public interface TeacherMapper {
    int deleteByPrimaryKey(Integer tid);

    int insert(Teacher record);

    Teacher selectByPrimaryKey(Integer tid);

    List<Teacher> selectAll();

    int updateByPrimaryKey(Teacher record);
}