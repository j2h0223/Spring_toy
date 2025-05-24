package com.toy.crud.controller;

import com.toy.crud.dto.StudentInputDtoForm;
import com.toy.crud.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    private final RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping("/student/register")
    public String viewRegisterStudent(){
        return "student/register";
    }

    @PostMapping("/student/register")
    public String registerStudent(@ModelAttribute StudentInputDtoForm form) {

        registerService.process(form);

        return "redirect:/";
    }
}
