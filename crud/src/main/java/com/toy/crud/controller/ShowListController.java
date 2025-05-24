package com.toy.crud.controller;

import com.toy.crud.dto.StudentInputDtoForm;
import com.toy.crud.dto.StudentOutputDtoForm;
import com.toy.crud.service.GetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ShowListController {

    private final GetListService getListService;

    @Autowired
    public ShowListController(GetListService getListService) {
        this.getListService = getListService;
    }

    @GetMapping("/student")
    public String showStudentList(Model model){
        List<StudentOutputDtoForm> studentOutputDtoFormList = getListService.process();
        model.addAttribute("students", studentOutputDtoFormList);
        return "student/list";
    }

    @GetMapping("/student/back")
    public String goHome(){
        return "redirect:/";
    }




}
