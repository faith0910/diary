package com.shine.mapper;

import com.shine.entity.Class;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(Class record);

    Class selectByPrimaryKey(Integer cid);

    List<Class> selectAll();

    int updateByPrimaryKey(Class record);

    Class getClass(Integer cid);
}