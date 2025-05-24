package com.toy.toycinema.main.service;

import com.toy.toycinema.dto.*;

import com.toy.toycinema.main.mapper.RestReservationFilmMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class RestReservationFilmServiceImpl implements RestReservationFilmService {

    private final RestReservationFilmMapper restReservationFilmMapper;

    public RestReservationFilmServiceImpl(RestReservationFilmMapper restReservationFilmMapper) {
        this.restReservationFilmMapper = restReservationFilmMapper;
    }

    @Override
    public FilmPlayingTableDto getFilmPlayingTableDto(FilmPlayingTableDto filmPlayingTableDto) {
        return restReservationFilmMapper.selectFilmPlayingTableDtoByFilmPlayingTable(filmPlayingTableDto);
    }

    @Override
    public Map<String, Object> getPlayingTableInfoCanvas(FilmPlayingTableDto param) {
        Map<String, Object> map = new HashMap<>();

        System.out.println("param = " + param);

        BoxDto boxDto = restReservationFilmMapper.selectBoxDtoByFilmPlayingTable(param);
        TypeDto typeDto = restReservationFilmMapper.selectTypeDtoByFilmPlayingTable(param);
        TheaterDto theaterDto = restReservationFilmMapper.selectTheaterDtoByFilmPlayingTable(param);
        FilmPlayingTableDto filmPlayingTableDto = restReservationFilmMapper.selectFilmPlayingTableDtoByFilmPlayingTable(param);

        Integer bookedSeatCount = restReservationFilmMapper.countFilmReservationSeatByFilmPlayingTable(param);
        FilmDto filmDto = restReservationFilmMapper.selectFilmDtoByFilmPlayingTable(param);

        map.put("typeDto", typeDto);
        map.put("boxDto", boxDto);
        map.put("filmPlayingTableDto", filmPlayingTableDto);
        map.put("bookedSeatCount", bookedSeatCount);
        map.put("filmDto", filmDto);
        map.put("theaterDto", theaterDto);

        return map;
    }

    @Override
    public Map<String, Object> getBoxAndTypeInfo(FilmPlayingTableDto param) {
        Map<String, Object> map = new HashMap<>();

        TypeDto typeDto = restReservationFilmMapper.selectTypeDtoByFilmPlayingTable(param);
        BoxDto boxDto = restReservationFilmMapper.selectBoxDtoByFilmPlayingTable(param);
        FilmPlayingTableDto filmPlayingTableDto = restReservationFilmMapper.selectFilmPlayingTableDtoByFilmPlayingTable(param);

        map.put("typeDto", typeDto);
        map.put("boxDto", boxDto);
        map.put("filmPlayingTableDto", filmPlayingTableDto);

        return map;
    }

    @Override
    public List<Map<String, Object>> getPlayingTableInfo(SelectFilmReservationDto selectFilmReservationDto) {
        List<Map<String, Object>> list = new ArrayList<>();

        if (selectFilmReservationDto.getDate().equals(LocalDate.now())) {
            // 날짜 선택되었지만 오늘이고 date + 현재 시간
            selectFilmReservationDto.setTime(LocalTime.now().withNano(0));
        } else {
            // 오늘이 아닌 다른 날이 선택 date + 0시00분
            selectFilmReservationDto.setTime(LocalTime.of(0, 0));
        }


        List<BoxTypeDto> boxTypeDtoList = restReservationFilmMapper.selectBoxTypeDtoListForReservation(selectFilmReservationDto);
        System.out.println("boxTypeDtoList = " + boxTypeDtoList);
        for (BoxTypeDto boxTypeDto : boxTypeDtoList) {
            if (selectFilmReservationDto.getDate().equals(LocalDate.now())) {
                // 날짜 선택되었지만 오늘이고 date + 현재 시간
                selectFilmReservationDto.setTime(LocalTime.now().withNano(0));
            } else {
                // 오늘이 아닌 다른 날이 선택 date + 0시00분
                selectFilmReservationDto.setTime(LocalTime.of(0, 0));
            }
            Map<String, Object> map = new HashMap<>();

            BoxDto boxDto = restReservationFilmMapper.selectBoxDtoForReservation(boxTypeDto);
            TypeDto typeDto = restReservationFilmMapper.selectTypeDtoForReservation(boxTypeDto);

            selectFilmReservationDto.setBoxTypeId(boxTypeDto.getId());
            List<TimeDto> timeDtoList = restReservationFilmMapper.selectTimeDtoListForReservation(selectFilmReservationDto);
            List<Map<String, Object>> timeAndSeatCountList = new ArrayList<>();
            for (TimeDto timeDto : timeDtoList) {
                Map<String, Object> timeAndSeatCountMap = new HashMap<>();

                selectFilmReservationDto.setTime(timeDto.getTime());
                Integer bookedSeatCount = restReservationFilmMapper.countFilmReservationSeatForReservation(selectFilmReservationDto);
                FilmPlayingTableDto filmPlayingTableDto = restReservationFilmMapper.selectFilmPlayingTableDtoForReservation(selectFilmReservationDto);

                timeAndSeatCountMap.put("filmPlayingTableDto", filmPlayingTableDto);
                timeAndSeatCountMap.put("timeDto", timeDto);
                timeAndSeatCountMap.put("bookedSeatCount", bookedSeatCount);
                timeAndSeatCountList.add(timeAndSeatCountMap);
            }

//            System.out.println("boxTypeDto = " + boxTypeDto);
//            System.out.println("boxDto = " + boxDto);
//            System.out.println("typeDto = " + typeDto);
//            System.out.println("timeDtoList = " + timeDtoList);
//            System.out.println("timeAndSeatCountList = " + timeAndSeatCountList);

            map.put("boxDto", boxDto);
            map.put("typeDto", typeDto);
//            map.put("timeDtoList", timeDtoList);
            map.put("timeAndSeatCountList", timeAndSeatCountList);
            list.add(map);
        }

        return list;
    }

    @Override
    public Map<String, Object> getSelectedTheaterInfo(TheaterDto param) {
        Map<String, Object> map = new HashMap<>();

        TheaterDto theaterDto = restReservationFilmMapper.selectTheaterDtoByTheater(param);
//        System.out.println("theaterDto = " + theaterDto);
        map.put("theaterDto", theaterDto);
        return map;
    }

    @Override
    public Map<String, Object> getSelectedFilmInfo(FilmDto param) {
        Map<String, Object> map = new HashMap<>();

        FilmDto filmDto = restReservationFilmMapper.selectFilmDtoByFilm(param);
        FilmPosterDto filmPosterDto = restReservationFilmMapper.selectFilmPosterDtoByFilm(param);


        map.put("filmDto", filmDto);
        map.put("filmPosterDto", filmPosterDto);

//        System.out.println("filmDto = " + filmDto);
//        System.out.println("filmPosterDto = " + filmPosterDto);

        return map;
    }

    @Override
    public Map<String, Object> getFilmTheaterDateBoxTypeTime(SelectFilmReservationDto selectFilmReservationDto) {

        Map<String, Object> map = new HashMap<>();

        List<TheaterDto> theaterDtoList = new ArrayList<>();
        List<FilmDto> filmDtoList = new ArrayList<>();
        List<DateDto> dateDtoList = new ArrayList<>();

        List<TheaterDto> theaterDtoWhiteList = new ArrayList<>();
        List<FilmDto> filmDtoWhiteList = new ArrayList<>();
        List<DateDto> dateDtoWhiteList = new ArrayList<>();


        if (selectFilmReservationDto.getDate() == null) {
            // 날짜 선택 안되었을 경우 today + 현재 시간
            selectFilmReservationDto.setTime(LocalTime.now().withNano(0));
        } else {
            if (selectFilmReservationDto.getDate().equals(LocalDate.now())) {
                // 날짜 선택되었지만 오늘이고 date + 현재 시간
                selectFilmReservationDto.setTime(LocalTime.now().withNano(0));
            } else {
                // 오늘이 아닌 다른 날이 선택 date + 0시00분
                selectFilmReservationDto.setTime(LocalTime.of(0, 0));
            }
        }

        switch (makeFlag(selectFilmReservationDto)) {
            case 0b000 -> {
                // 아무것도 선택되지 않은
                dateDtoList = restReservationFilmMapper.selectAllDataDtoList(selectFilmReservationDto);
                filmDtoList = restReservationFilmMapper.selectAllFilmDtoList(selectFilmReservationDto);
                theaterDtoList = restReservationFilmMapper.selectAllTheaterDtoList(selectFilmReservationDto);

                dateDtoWhiteList = restReservationFilmMapper.selectAllDataDtoList(selectFilmReservationDto);
            }

            case 0b001 -> {
                // 날짜만 선택
                dateDtoList = restReservationFilmMapper.selectAllDataDtoList(selectFilmReservationDto);
                dateDtoWhiteList = restReservationFilmMapper.selectAllDataDtoList(selectFilmReservationDto);

                filmDtoList = restReservationFilmMapper.selectFilmDtoListByDate(selectFilmReservationDto);
                filmDtoWhiteList = restReservationFilmMapper.selectFilmDtoWhiteListByDate(selectFilmReservationDto);

                theaterDtoList = restReservationFilmMapper.selectTheaterDtoListByDate(selectFilmReservationDto);
                theaterDtoWhiteList = restReservationFilmMapper.selectTheaterDtoWhiteListByDate(selectFilmReservationDto);
            }

            case 0b010 -> {
                // 영화관만 선택
                theaterDtoList = restReservationFilmMapper.selectAllTheaterDtoList(selectFilmReservationDto);

                filmDtoList = restReservationFilmMapper.selectFilmDtoListByTheater(selectFilmReservationDto);
                filmDtoWhiteList = restReservationFilmMapper.selectFilmDtoWhiteListByTheater(selectFilmReservationDto);

                dateDtoList = restReservationFilmMapper.selectDateDtoListByTheater(selectFilmReservationDto);
                dateDtoWhiteList = restReservationFilmMapper.selectAllDataDtoList(selectFilmReservationDto);
            }

            case 0b100 -> {
                // 영화만 선택
                filmDtoList = restReservationFilmMapper.selectAllFilmDtoList(selectFilmReservationDto);

                theaterDtoList = restReservationFilmMapper.selectTheaterDtoListByFilm(selectFilmReservationDto);
                theaterDtoWhiteList = restReservationFilmMapper.selectTheaterDtoWhiteListByFilm(selectFilmReservationDto);

                dateDtoList = restReservationFilmMapper.selectDateDtoListByFilm(selectFilmReservationDto);
                dateDtoWhiteList = restReservationFilmMapper.selectAllDataDtoList(selectFilmReservationDto);
            }

            case 0b011 -> {
                // 극장, 날짜 선택
                    // 극장의 경우 - 날짜 선택만 한것과 동일
                theaterDtoList = restReservationFilmMapper.selectTheaterDtoListByDate(selectFilmReservationDto);
                theaterDtoWhiteList = restReservationFilmMapper.selectTheaterDtoWhiteListByDate(selectFilmReservationDto);

                    // 날짜도??? - 극장만 선택한것과 동일?
                dateDtoList = restReservationFilmMapper.selectDateDtoListByTheater(selectFilmReservationDto);
                dateDtoWhiteList = restReservationFilmMapper.selectAllDataDtoList(selectFilmReservationDto);

                // 영화
                filmDtoList = restReservationFilmMapper.selectFilmDtoListByTheaterAndDate(selectFilmReservationDto);
                filmDtoWhiteList = restReservationFilmMapper.selectFilmDtoWhiteListByTheaterAndDate(selectFilmReservationDto);
            }

            case 0b101 -> {
                // 날짜, 영화 선택
                    // 영화의 경우 - 날짜만 선택한 것과 동일
                filmDtoList = restReservationFilmMapper.selectFilmDtoListByDate(selectFilmReservationDto);
                filmDtoWhiteList = restReservationFilmMapper.selectFilmDtoWhiteListByDate(selectFilmReservationDto);
                    // 날짜의 경우 - 영화만 선택한 것과 동일
                dateDtoList = restReservationFilmMapper.selectDateDtoListByFilm(selectFilmReservationDto);
                dateDtoWhiteList = restReservationFilmMapper.selectAllDataDtoList(selectFilmReservationDto);

                theaterDtoList = restReservationFilmMapper.selectTheaterDtoListByFilmAndDate(selectFilmReservationDto);
                theaterDtoWhiteList = restReservationFilmMapper.selectTheaterDtoWhiteListByFilmAndDate(selectFilmReservationDto);
            }

            case 0b110 -> {
                // 영화, 극장 선택
                    // 영화의 경우 - 극장만 선택한 것과 동일
                filmDtoList = restReservationFilmMapper.selectFilmDtoListByTheater(selectFilmReservationDto);
                filmDtoWhiteList = restReservationFilmMapper.selectFilmDtoWhiteListByTheater(selectFilmReservationDto);
                    // 극장의 경우 - 영화만 선택한 것과 동일
                theaterDtoList = restReservationFilmMapper.selectTheaterDtoListByFilm(selectFilmReservationDto);
                theaterDtoWhiteList = restReservationFilmMapper.selectTheaterDtoWhiteListByFilm(selectFilmReservationDto);

                dateDtoList = restReservationFilmMapper.selectDateDtoListByFilmAndTheater(selectFilmReservationDto);
                dateDtoWhiteList = restReservationFilmMapper.selectAllDataDtoList(selectFilmReservationDto);
            }

            case 0b111 -> {
                // 전체 선택
                    // 극장의 경우 - 영화/날짜만 선택한 것과 동일
                theaterDtoList = restReservationFilmMapper.selectTheaterDtoListByFilmAndDate(selectFilmReservationDto);
                theaterDtoWhiteList = restReservationFilmMapper.selectTheaterDtoWhiteListByFilmAndDate(selectFilmReservationDto);
                    // 영화의 경우 - 극장/날짜만 선택한 것과 동일
                filmDtoList = restReservationFilmMapper.selectFilmDtoListByTheaterAndDate(selectFilmReservationDto);
                filmDtoWhiteList = restReservationFilmMapper.selectFilmDtoWhiteListByTheaterAndDate(selectFilmReservationDto);
                    // 날짜의 경우 - 극장/영화만 선택한 것과 동일
                dateDtoList = restReservationFilmMapper.selectDateDtoListByFilmAndTheater(selectFilmReservationDto);
                dateDtoWhiteList = restReservationFilmMapper.selectAllDataDtoList(selectFilmReservationDto);
            }


        }

//        System.out.println();
//        System.out.println("dateDtoList = " + dateDtoList);
        map.put("dateDtoList", dateDtoList);

//        System.out.println("filmDtoList = " + filmDtoList);
        map.put("filmDtoList", filmDtoList);

//        System.out.println("theaterDtoList = " + theaterDtoList);
        map.put("theaterDtoList", theaterDtoList);


//        System.out.println();

        map.put("dateDtoWhiteList", dateDtoWhiteList);
//        System.out.println("dateDtoWhiteList = " + dateDtoWhiteList);

        map.put("filmDtoWhiteList", filmDtoWhiteList);
//        System.out.println("filmDtoWhiteList = " + filmDtoWhiteList);

        map.put("theaterDtoWhiteList", theaterDtoWhiteList);
//        System.out.println("theaterDtoWhiteList = " + theaterDtoWhiteList);

        return map;
    }

    private int makeFlag(SelectFilmReservationDto selectFilmReservationDto) {
        int filmId = selectFilmReservationDto.getFilmId();
        int theaterId = selectFilmReservationDto.getTheaterId();
        LocalDate date = selectFilmReservationDto.getDate();

        // 3비트 조합: filmId(4) + theaterId(2) + date(1)
        int flag = 0;
        if (filmId != 0) flag |= 4;
        if (theaterId != 0) flag |= 2;
        if (date != null) flag |= 1;

        return flag;
    }
}
