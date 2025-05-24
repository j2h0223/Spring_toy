package com.toy.toycinema.operator.controller;

import com.toy.toycinema.dto.*;

import com.toy.toycinema.operator.service.OperatorRegisterFilmPlayingTableService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("operator")
public class OperatorRegisterFilmPlayingTableController {

    private final OperatorRegisterFilmPlayingTableService operatorRegisterFilmPlayingTableService;

    public OperatorRegisterFilmPlayingTableController(OperatorRegisterFilmPlayingTableService operatorRegisterFilmPlayingTableService) {
        this.operatorRegisterFilmPlayingTableService = operatorRegisterFilmPlayingTableService;
    }

    @RequestMapping("selectFilmPlayingTablePage")
    public String selectFilmPlayingTablePage(
            HttpSession session,
            Model model
    ) {

        List<Map<String, Object>> filmAndTypeInfo = operatorRegisterFilmPlayingTableService.getFilmAndTypeInfo();
        model.addAttribute("filmAndTypeInfo", filmAndTypeInfo);

        return "operator/table/selectFilmPlayingTablePage";
    }

    @RequestMapping("selectFilmPlayingTableProcess")
    public String selectFilmPlayingTableProcess(
            @RequestParam("selectedValue") String selectedValue,
            HttpSession session,
            Model model
    ) {
        String[] parts = selectedValue.split("-");
        int filmId = Integer.parseInt(parts[0]);
        int typeId = Integer.parseInt(parts[1]);

        FilmPlayingTableDto filmPlayingTableDto = new FilmPlayingTableDto();
        filmPlayingTableDto.setFilmId(filmId);
        filmPlayingTableDto.setTypeId(typeId);

        List<Map<String, Object>> theaterAndBoxAndTypeInfo = operatorRegisterFilmPlayingTableService.getTheaterAndBoxAndTypeInfo(filmPlayingTableDto);
        model.addAttribute("theaterAndBoxAndTypeInfo", theaterAndBoxAndTypeInfo);

        FilmDto filmDto = new FilmDto();
        filmDto.setId(filmId);
        filmDto = operatorRegisterFilmPlayingTableService.getFilmDtoByFilmId(filmDto);

        TypeDto typeDto = new TypeDto();
        typeDto.setId(typeId);
        typeDto = operatorRegisterFilmPlayingTableService.getTypeDtoByTypeId(typeDto);

        session.setAttribute("filmDto", filmDto);
        session.setAttribute("typeDto", typeDto);

        return "operator/table/selectTheaterBoxPlayingTablePage";
    }

    @RequestMapping("selectTheaterBoxPlayingTableProcess")
    public String selectTheaterBoxPlayingTableProcess(
            @RequestParam("selectedValue") String selectedValue,
            HttpSession session
    ) {
        String[] parts = selectedValue.split("-");
        int theaterId = Integer.parseInt(parts[0]);
        int boxId = Integer.parseInt(parts[1]);

        TheaterDto theaterDto = new TheaterDto();
        theaterDto.setId(theaterId);
        theaterDto = operatorRegisterFilmPlayingTableService.getTheaterDtoByTheaterId(theaterDto);

        BoxDto boxDto = new BoxDto();
        boxDto.setId(boxId);
        boxDto = operatorRegisterFilmPlayingTableService.getBoxDtoByBoxId(boxDto);

        BoxTypeDto boxTypeDto = new BoxTypeDto();
        TypeDto typeDto = (TypeDto) session.getAttribute("typeDto");
        boxTypeDto.setTypeId(typeDto.getId());
        boxTypeDto.setBoxId(boxDto.getId());
        boxTypeDto = operatorRegisterFilmPlayingTableService.getBoxTypeDtoByBoxTypeDto(boxTypeDto);

        session.setAttribute("theaterDto", theaterDto);
        session.setAttribute("boxDto", boxDto);
        session.setAttribute("boxTypeDto", boxTypeDto);

        return "operator/table/registerDetailInfoPlayingTablePage";
    }

    @RequestMapping("registerDetailInfoPlayingTableProcess")
    public String registerDetailInfoPlayingTableProcess(
            @ModelAttribute FilmPlayingTableDto filmPlayingTableDto,
            HttpSession session
    ) {
        System.out.println("filmPlayingTableDto = " + filmPlayingTableDto);


        FilmDto filmDto = (FilmDto) session.getAttribute("filmDto");
        filmPlayingTableDto.setFilmId(filmDto.getId());

        BoxTypeDto boxTypeDto = (BoxTypeDto) session.getAttribute("boxTypeDto");
        filmPlayingTableDto.setBoxTypeId(boxTypeDto.getId());

        System.out.println("filmPlayingTableDto = " + filmPlayingTableDto);
        operatorRegisterFilmPlayingTableService.registerFIlmPlayingTable(filmPlayingTableDto);

//        return "operator/table/registerTheaterBoxPlayingTableSuccessPage";
        return "operator/mainPage";
    }
}
