package com.toy.toycinema.operator.controller;

import com.toy.toycinema.dto.*;

import com.toy.toycinema.operator.service.OperatorAuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("operator")
public class OperatorAutController {

    private final OperatorAuthService operatorAuthService;

    public OperatorAutController(OperatorAuthService operatorAuthService) {
        this.operatorAuthService = operatorAuthService;
    }

    @RequestMapping("loginPage")
    public String operatorLogin() {

        return "/operator/loginPage";
    }

    @RequestMapping("registerPage")
    public String operatorRegister() {

        return "/operator/registerPage";
    }

    @RequestMapping("registerProcess")
    public String registerProcess(
            @ModelAttribute OperatorDto operatorDto
    ) {
        operatorAuthService.registerOperator(operatorDto);
        return "/operator/registerSuccessPage";
    }

    @RequestMapping("loginProcess")
    public String loginProcess(
            @ModelAttribute OperatorDto operatorDto,
            HttpSession session
    ) {
        OperatorDto operator = operatorAuthService.loginOperator(operatorDto);
        System.out.println("operator = " + operator);
        if (operator == null) {
            return "redirect:/operator/loginPage";
        } else {
            session.setAttribute("operatorLoginInfo", operator);
            return "redirect:/operator/mainPage";
        }
    }
}