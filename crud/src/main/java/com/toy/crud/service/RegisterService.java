package com.toy.crud.service;

import com.toy.crud.dto.StudentInputDtoForm;

public interface RegisterService {
    public abstract void process(StudentInputDtoForm form);
}
