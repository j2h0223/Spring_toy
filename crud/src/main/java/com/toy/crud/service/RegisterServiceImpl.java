package com.toy.crud.service;

import com.toy.crud.dto.StudentInputDtoForm;
import com.toy.crud.dto.StudentInputDto;
import com.toy.crud.mapper.RegisterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

@Service
public class RegisterServiceImpl implements RegisterService {

    private final RegisterMapper registerMapper;

    @Autowired
    public RegisterServiceImpl(RegisterMapper registerMapper) {
        this.registerMapper = registerMapper;
    }


    @Override
    public void process(@ModelAttribute StudentInputDtoForm form) {
        String name = form.getName();
        int age = form.getAge();
        int score = form.getScore();

        StudentInputDto student = new StudentInputDto(name, age, score);

        registerMapper.save(student);
    }
}
