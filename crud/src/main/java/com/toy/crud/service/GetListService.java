package com.toy.crud.service;

import com.toy.crud.dto.StudentOutputDtoForm;

import java.util.List;

public interface GetListService {
    List<StudentOutputDtoForm> process();
}
