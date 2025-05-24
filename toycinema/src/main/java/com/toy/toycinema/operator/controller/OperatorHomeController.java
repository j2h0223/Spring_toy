package com.toy.toycinema.operator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("operator")
public class OperatorHomeController {
    @RequestMapping("homePage")
    public String operatorHomePage() {
        return "/operator/homePage";
    }

    @RequestMapping("mainPage")
    public String operatorMainPage() {
        return "/operator/mainPage";
    }

    @RequestMapping("filmList")
    public String  filmList() {
        return "/operator/list/filmListPage";
    }

    @RequestMapping("restFilmRegister")
    public String restFilmRegister() {
        return "/operator/rest/restFilmRegisterPage";
    }

}
