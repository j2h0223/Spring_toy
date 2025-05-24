package com.toy.toycinema.operator.service;

import com.toy.toycinema.dto.*;

import com.toy.toycinema.operator.mapper.OperatorSqlMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OperatorRegisterFilmPlayingTableServiceImpl implements OperatorRegisterFilmPlayingTableService {
    private final OperatorSqlMapper operatorSqlMapper;

    public OperatorRegisterFilmPlayingTableServiceImpl(OperatorSqlMapper operatorSqlMapper) {
        this.operatorSqlMapper = operatorSqlMapper;
    }

    @Override
    public void registerFIlmPlayingTable(FilmPlayingTableDto filmPlayingTableDto) {
        operatorSqlMapper.insertFIlmPlayingTable(filmPlayingTableDto);
    }

    @Override
//    public Map<String, Object> getTheaterAndBoxAndTypeInfo(FilmPlayingTableDto filmPlayingTableDto) {
    public List<Map<String, Object>> getTheaterAndBoxAndTypeInfo(FilmPlayingTableDto filmPlayingTableDto) {
//        Map<String, Object> map = new HashMap<>();

        List<Map<String, Object>> boxDtoAndTheaterDtoInList = new ArrayList<>();

        BoxTypeDto boxTypeDto = new BoxTypeDto();
        boxTypeDto.setTypeId(filmPlayingTableDto.getTypeId());

//        FilmDto filmDto = new FilmDto();
//        filmDto.setId(filmPlayingTableDto.getFilmId());
//        filmDto = filmSqlMapper.selectFilmDtoByFilmId(filmDto);
//        map.put("filmDto", filmDto);

//        TypeDto typeDto = new TypeDto();
//        typeDto.setId(filmPlayingTableDto.getTypeId());
//        typeDto = operatorSqlMapper.selectTypeDtoByID(typeDto);
//        map.put("typeDto", typeDto);

        List<BoxTypeDto> boxTypeDtoList = operatorSqlMapper.selectAllBoxTypeByTypeId(boxTypeDto);
        for (BoxTypeDto searchedBoxTypeDto : boxTypeDtoList) {
            BoxDto boxDto = new BoxDto();
            boxDto.setId(searchedBoxTypeDto.getBoxId());
            boxDto = operatorSqlMapper.selectBoxDtoByBoxId(boxDto);

            TheaterDto theaterDto = new TheaterDto();
            theaterDto.setId(boxDto.getTheaterId());
            theaterDto = operatorSqlMapper.selectTheaterDtoByTheaterID(theaterDto);

//            System.out.println("boxDto = " + boxDto);
//            System.out.println("theaterDto = " + theaterDto);
//            System.out.println("searchedBoxTypeDto = " + searchedBoxTypeDto);

            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("boxDto", boxDto);
            tempMap.put("theaterDto", theaterDto);
            boxDtoAndTheaterDtoInList.add(tempMap);
        }
//        map.put("boxDtoAndTheaterDtoInList", boxDtoAndTheaterDtoInList);

        return boxDtoAndTheaterDtoInList;
    }



    @Override
    public List<Map<String, Object>> getFilmAndTypeInfo() {
        List<Map<String, Object>> list = new ArrayList<>();

        List<FilmDto> filmDtoList = operatorSqlMapper.selectFilmIsPlaying();
        for (FilmDto filmDto : filmDtoList) {
            FilmTypeDto filmTypeDtoForSearch = new FilmTypeDto();
            filmTypeDtoForSearch.setFilmId(filmDto.getId());

            List<TypeDto> typeDtoList = new ArrayList<>();

            List<FilmTypeDto> filmTypeDtoList = operatorSqlMapper.selectAllFilmTypeByFilmId(filmTypeDtoForSearch);
            for (FilmTypeDto filmTypeDto : filmTypeDtoList) {
                TypeDto typeDto = new TypeDto();
                typeDto.setId(filmTypeDto.getTypeId());
                typeDto = operatorSqlMapper.selectTypeDtoByTypeId(typeDto);
                typeDtoList.add(typeDto);
            }


            Map<String, Object> map = new HashMap<>();
            map.put("filmDto", filmDto);
//            map.put("filmTypeDtoList", filmTypeDtoList);
            map.put("typeDtoList", typeDtoList);
            list.add(map);
        }

        return list;
    }

    @Override
    public FilmDto getFilmDtoByFilmId(FilmDto filmDto) {
        return operatorSqlMapper.selectFilmDtoByFilmId(filmDto);
    }

    @Override
    public TypeDto getTypeDtoByTypeId(TypeDto typeDto) {
        return operatorSqlMapper.selectTypeDtoByTypeId(typeDto);
    }

    @Override
    public TheaterDto getTheaterDtoByTheaterId(TheaterDto theaterDto) {
        return operatorSqlMapper.selectTheaterDtoByTheaterID(theaterDto);
    }

    @Override
    public BoxDto getBoxDtoByBoxId(BoxDto boxDto) {
        return operatorSqlMapper.selectBoxDtoByBoxId(boxDto);
    }

    @Override
    public BoxTypeDto getBoxTypeDtoByBoxTypeDto(BoxTypeDto boxTypeDto) {
        return operatorSqlMapper.selectBoxTypeDtoByBoxIdAndTypeId(boxTypeDto);
    }

}
