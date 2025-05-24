package com.toy.toycinema.operator.controller;

import com.toy.toycinema.dto.*;

import com.toy.toycinema.operator.service.OperatorTheaterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("operator/theater")
public class OperatorRegisterTheaterController {

    private final OperatorTheaterService operatorTheaterService;

    public OperatorRegisterTheaterController(OperatorTheaterService operatorTheaterService) {
        this.operatorTheaterService = operatorTheaterService;
    }

    @RequestMapping("registerTheaterPage")
    public String registerTheaterPage() {
        return "/operator/theater/registerTheaterPage";
    }

    @RequestMapping("registerTheaterProcess")
    public String registerTheaterProcess(
            @ModelAttribute TheaterDto theaterDto
    ) {
        operatorTheaterService.registerTheater(theaterDto);

        return "redirect:/operator/theater/registerTheaterSuccessPage";
    }

    @RequestMapping("registerTheaterSuccessPage")
    public String registerTheaterSuccessPage(
            @ModelAttribute TheaterDto theaterDto,
            Model model
    ) {
        model.addAttribute("theaterDto", theaterDto);

        return "/operator/theater/registerTheaterSuccessPage";
    }

    @RequestMapping("registerBoxBasicInfoPage")
    public String registerBoxPage(
            Model model
    ) {
        List<TheaterDto> theaterDtoList = operatorTheaterService.findAllTheater();


        model.addAttribute("theaterDtoList", theaterDtoList);

        return "/operator/theater/registerBoxBasicInfoPage";
    }

    @RequestMapping("registerBoxBasicInfoProcess")
    public String registerBoxBasicInfoProcess(
            @ModelAttribute RegisterBoxDto registerBoxDto,
            Model model
    ) {
        System.out.println("registerBoxDto = " + registerBoxDto);
        operatorTheaterService.registerBoxBasicInfo(registerBoxDto);

        registerBoxDto.setTypeDtoList(new ArrayList<>(Collections.nCopies(registerBoxDto.getCountType(), new TypeDto())));
        System.out.println("registerBoxDto = " + registerBoxDto);

        model.addAttribute("registerBoxDto", registerBoxDto);

        return "/operator/theater/registerBoxDetailInfoPage";
    }

    @RequestMapping("registerBoxDetailInfoProcess")
    public String registerBoxSuccessPage(
            @ModelAttribute RegisterBoxDto registerBoxDto,
            Model model
    ) {
        System.out.println("registerBoxDto = " + registerBoxDto);

        operatorTheaterService.registerBoxDetailInfo(registerBoxDto);

//        model.addAttribute("boxDto", boxDto);

        return "operator/mainPage";
    }
}
