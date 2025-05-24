package com.toy.crud.mapper;

import com.toy.crud.dto.StudentDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ListMapper {

    @Select("select id, name, age, score from student")
    @Results(id = "studentMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "age", column = "age"),
            @Result(property = "score", column = "score")
    })
    List<StudentDto> findAll();
}
