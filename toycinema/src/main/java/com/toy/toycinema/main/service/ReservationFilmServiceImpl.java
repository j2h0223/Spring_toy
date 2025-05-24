package com.toy.toycinema.main.service;

import com.toy.toycinema.dto.*;

import com.toy.toycinema.main.mapper.MainPageSqlMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReservationFilmServiceImpl implements ReservationFilmService {

    private final MainPageSqlMapper mainPageSqlMapper;

    public ReservationFilmServiceImpl(MainPageSqlMapper mainPageSqlMapper) {
        this.mainPageSqlMapper = mainPageSqlMapper;
    }

    @Override
    public FilmReservationDto getReservationInfo(FilmReservationDto filmReservationDto) {
        return mainPageSqlMapper.selectFilmReservationDto(filmReservationDto);
    }

    @Override
    public void setReservation(FilmReservationDto filmReservationDto) {
        mainPageSqlMapper.insertReservation(filmReservationDto);
    }

    @Override
    public Map<String, Object> getScreeningSchedule(SelectFilmReservationDto selectFilmReservationDto) {

        Map<String, Object> map = new HashMap<>();

        List<TheaterDto> theaterDtoList = new ArrayList<>();
        List<FilmDto> filmDtoList = new ArrayList<>();
        List<DateDto> dateDtoList = new ArrayList<>();

        List<BoxTypeDto> boxTypeDtoList = new ArrayList<>();
        List<BoxDto> boxDtoList = new ArrayList<>();

        List<TheaterDto> remainTheaterDtoList = new ArrayList<>();
        List<FilmDto> remainFilmDtoList = new ArrayList<>();
        List<DateDto> remainDateDtoList = new ArrayList<>();

        Set<LocalDate> remainDateSet = new HashSet<>();


        System.out.println("selectFilmReservationDto.getDate() = " + selectFilmReservationDto.getDate());

//        if (selectFilmReservationDto.getDate() == null) {
//            selectFilmReservationDto.setDate(LocalDate.now());
//            selectFilmReservationDto.setTime(LocalTime.now().withNano(0));
//        }
        
        if (selectFilmReservationDto.getTime() == null) {
            selectFilmReservationDto.setTime(LocalTime.of(0, 0));
        }

        System.out.println();

        System.out.println("makeFlag(selectFilmReservationDto) = " + makeFlag(selectFilmReservationDto));
        System.out.println("selectFilmReservationDto = " + selectFilmReservationDto);
        switch (makeFlag(selectFilmReservationDto)) {
            case 0b000 -> {
                // filmId = 0, theaterId = 0, date = null
                // 예 : 아무것도 선택 안됨
                theaterDtoList = mainPageSqlMapper.selectAllTheater(selectFilmReservationDto);
                filmDtoList = mainPageSqlMapper.selectAllFilm(selectFilmReservationDto);
                dateDtoList = mainPageSqlMapper.selectAllDate(selectFilmReservationDto);

                remainTheaterDtoList = mainPageSqlMapper.selectRemainTheaterListByPickDate(selectFilmReservationDto);
                remainFilmDtoList = mainPageSqlMapper.selectRemainFilmListByPickedDate(selectFilmReservationDto);
            }
            case 0b001 -> {
                //date만 선택됨
                theaterDtoList = mainPageSqlMapper.selectTheaterListByPickDate(selectFilmReservationDto);
                filmDtoList = mainPageSqlMapper.selectFilmListByPickedDate(selectFilmReservationDto);
                dateDtoList = mainPageSqlMapper.selectAllDate(selectFilmReservationDto);

                remainTheaterDtoList = mainPageSqlMapper.selectRemainTheaterListByPickDate(selectFilmReservationDto);
                remainFilmDtoList = mainPageSqlMapper.selectRemainFilmListByPickedDate(selectFilmReservationDto);

            }
            case 0b010 -> {
                // theaterId만 선택됨
                theaterDtoList = mainPageSqlMapper.selectAllTheater(selectFilmReservationDto);
                filmDtoList = mainPageSqlMapper.selectFilmListByPickedTheater(selectFilmReservationDto);
//                dateDtoList = mainPageSqlMapper.selectDateListByPickedTheater(selectFilmReservationDto);
                dateDtoList = mainPageSqlMapper.selectAllDate(selectFilmReservationDto);

//                remainDateDtoList = mainPageSqlMapper.
                remainFilmDtoList = mainPageSqlMapper.selectRemainFilmListByPickedTheater(selectFilmReservationDto);

            }
            case 0b011 -> {
                // theaterId, date만 선택됨
                theaterDtoList = mainPageSqlMapper.selectAllTheater(selectFilmReservationDto);
                filmDtoList = mainPageSqlMapper.selectFilmListByPickedTheater(selectFilmReservationDto);
                dateDtoList = mainPageSqlMapper.selectDateListByPickedTheater(selectFilmReservationDto);

                remainFilmDtoList = mainPageSqlMapper.selectRemainFilmListByPickedTheater(selectFilmReservationDto);
                remainTheaterDtoList = mainPageSqlMapper.selectRemainTheaterListByPickDate(selectFilmReservationDto);
            }
            case 0b100 -> {
                // filmId만 선택됨
                theaterDtoList = mainPageSqlMapper.selectTheaterListByPickedFilm(selectFilmReservationDto);
                filmDtoList = mainPageSqlMapper.selectAllFilm(selectFilmReservationDto);
//                dateDtoList = mainPageSqlMapper.selectDateListByPickedFilm(selectFilmReservationDto);
                dateDtoList = mainPageSqlMapper.selectAllDate(selectFilmReservationDto);

                remainTheaterDtoList = mainPageSqlMapper.selectRemainTheaterListByPickedFilm(selectFilmReservationDto);
                remainDateDtoList = mainPageSqlMapper.selectRemainDateListByPickedFilm(selectFilmReservationDto);

                remainDateSet = remainDateDtoList.stream()
                        .map(DateDto::getDate)
                        .collect(Collectors.toSet());
//                model.addAttribute("remainDateSet", remainDateSet);
            }
            case 0b101 -> {
                // filmId, date만 선택됨
                theaterDtoList = mainPageSqlMapper.selectTheaterListByPickedFilm(selectFilmReservationDto);
                filmDtoList = mainPageSqlMapper.selectAllFilm(selectFilmReservationDto);
                dateDtoList = mainPageSqlMapper.selectDateListByPickedFilm(selectFilmReservationDto);

                System.out.println(" = ");
                remainTheaterDtoList = mainPageSqlMapper.selectRemainTheaterListByPickedFilm(selectFilmReservationDto);
            }
            case 0b110 -> {
                // filmId, theaterId만 선택됨
                theaterDtoList = mainPageSqlMapper.selectTheaterListByPickedTheaterAndFilm(selectFilmReservationDto);
                filmDtoList = mainPageSqlMapper.selectFilmListByPickedTheaterAndFilm(selectFilmReservationDto);
                dateDtoList = mainPageSqlMapper.selectDateListByPickedTheaterAndFilm(selectFilmReservationDto);
            }
            case 0b111 -> {
                // filmId, theaterId, date 모두 선택됨
                theaterDtoList = mainPageSqlMapper.selectTheaterListByPickedTheaterAndFilm(selectFilmReservationDto);
                filmDtoList = mainPageSqlMapper.selectFilmListByPickedTheaterAndFilm(selectFilmReservationDto);
                dateDtoList = mainPageSqlMapper.selectDateListByPickedTheaterAndFilm(selectFilmReservationDto);
                boxTypeDtoList = mainPageSqlMapper.selectBoxTypeByPickedTheaterAndFilmAndDate(selectFilmReservationDto);
                boxDtoList = mainPageSqlMapper.selectBoxByPickedTheaterAndFilmAndDate(selectFilmReservationDto);
            }
        }


        System.out.println("theaterDtoList = " + theaterDtoList);
        System.out.println("filmDtoList = " + filmDtoList);
        System.out.println("dateDtoList = " + dateDtoList);

        System.out.println("remainTheaterDtoList = " + remainTheaterDtoList);
        System.out.println("remainFilmDtoList = " + remainFilmDtoList);
        System.out.println("remainDateDtoList = " + remainDateDtoList);
        System.out.println("remainDateSet = " + remainDateSet);


        map.put("theaterDtoList", theaterDtoList);
        map.put("filmDtoList", filmDtoList);
        map.put("dateDtoList", dateDtoList);
        map.put("boxTypeDtoList", boxTypeDtoList);
        map.put("boxDtoList", boxDtoList);

        map.put("remainTheaterDtoList", remainTheaterDtoList);
        map.put("remainFilmDtoList", remainFilmDtoList);
        map.put("remainDateDtoList", remainDateDtoList);
        map.put("remainDateSet", remainDateSet);

        return map;
    }

    private int makeFlag(SelectFilmReservationDto selectFilmReservationDto) {

        int filmId = selectFilmReservationDto.getFilmId();
        int theaterId = selectFilmReservationDto.getTheaterId();
        LocalDate date = selectFilmReservationDto.getDate();
//        System.out.println("date = " + date);

        // 3비트 조합: filmId(4) + theaterId(2) + date(1)
        int flag = 0;
        if (filmId != 0) flag |= 4;
        if (theaterId != 0) flag |= 2;
        if (date != null) flag |= 1;

        return flag;
    }
}
