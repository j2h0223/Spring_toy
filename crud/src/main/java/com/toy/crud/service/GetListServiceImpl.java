package com.toy.crud.service;


import com.toy.crud.dto.StudentDto;
import com.toy.crud.dto.StudentOutputDtoForm;
import com.toy.crud.mapper.ListMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class GetListServiceImpl implements GetListService{

    private final ListMapper listMapper;

    @Autowired
    public GetListServiceImpl(ListMapper listMapper) {
        this.listMapper = listMapper;
    }

//    @Override
//    public List<StudentDtoForm> process() {
//        List<StudentDtoForm> studentDtoFormList = new ArrayList<>();
//
//        for (StudentDto studentDto : listMapper.findAll()) {
//            long id = studentDto.getId();
//            String name = studentDto.getName();
//            int age = studentDto.getAge();
//            int score = studentDto.getScore();
//
//            studentDtoFormList.add(
//                    new StudentDtoForm(id, name, age, score));
//        }
//
//        return studentDtoFormList;
//    }

    @Override
    public List<StudentOutputDtoForm> process() {
//        List<StudentDto> students = listMapper.findAll();
//        Optional<List<StudentDto>> optionalStudents = Optional.ofNullable(students);
//
//        return optionalStudents
//                .filter(list -> !list.isEmpty())
//                .map(list -> list.stream()
//                        .map(s -> new StudentDtoForm(s.getId(), s.getName(), s.getAge(), s.getScore()))
//                        .collect(Collectors.toList()))
//                .orElseGet(() -> {
//                    log.info("저장된 학생이 없습니다.");
//                    return Collections.emptyList();
//                });

        List<StudentOutputDtoForm> studentDtoFormList = new ArrayList<>();
        Optional.ofNullable(listMapper.findAll()).ifPresentOrElse(
                studentDtolist->{
                    for (StudentDto dto : studentDtolist){
                        studentDtoFormList.add(
                                new StudentOutputDtoForm(
                                        dto.getId(), dto.getName(), dto.getAge(), dto.getScore()));
                    }
                },()->{
                    System.out.println("비어있습니다");
                }
        );

        return studentDtoFormList;
    }
//
//    @Override
//    public List<StudentOutputDtoForm> process() {
//        List<StudentOutputDtoForm> studentOutputDtoFormList = new ArrayList<>();
//
//        for (StudentDto studentDto : listMapper.findAll()) {
//            long id = studentDto.getId();
//            String name = studentDto.getName();
//            int age = studentDto.getAge();
//            int score = studentDto.getScore();
//
//            studentOutputDtoFormList.add(
//                    new StudentOutputDtoForm(id, name, age, score));
//        }
//
//        return studentOutputDtoFormList;
//    }
}


