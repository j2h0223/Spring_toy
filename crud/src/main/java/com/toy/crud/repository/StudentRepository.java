package com.toy.crud.repository;

import com.toy.crud.dto.StudentDto;

import java.util.Optional;

public interface StudentRepository {
    void save(StudentDto studentDto);

    Optional<StudentDto> findByName(String name);
    Optional<StudentDto> findAll();

}
