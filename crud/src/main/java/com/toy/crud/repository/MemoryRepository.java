package com.toy.crud.repository;

import com.toy.crud.dto.StudentDto;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class MemoryRepository implements StudentRepository{

    @Override
    public void save(StudentDto studentDto) {

    }

    @Override
    public Optional<StudentDto> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<StudentDto> findAll() {
        return Optional.empty();
    }
}
