package com.toy.crud.mapper;

import com.toy.crud.dto.StudentDto;
import com.toy.crud.dto.StudentInputDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface RegisterMapper {

    @Insert("insert into student(name, age, score) values (#{name}, #{age}, #{score})")
    void save(StudentInputDto student);

    @Select("select * from student where name=#{name}")
    Optional<StudentDto> findByName(String name);
}
