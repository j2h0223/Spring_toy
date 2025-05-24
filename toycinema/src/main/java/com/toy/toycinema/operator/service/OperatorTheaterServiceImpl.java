package com.toy.toycinema.operator.service;

import com.toy.toycinema.dto.*;

import com.toy.toycinema.operator.mapper.OperatorSqlMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperatorTheaterServiceImpl implements OperatorTheaterService{
    private final OperatorSqlMapper operatorSqlMapper;

    public OperatorTheaterServiceImpl(OperatorSqlMapper operatorSqlMapper) {
        this.operatorSqlMapper = operatorSqlMapper;
    }

    @Override
    public void registerTheater(TheaterDto theaterDto) {
        operatorSqlMapper.insertTheater(theaterDto);
    }


    @Override
    public List<TheaterDto> findAllTheater() {
        return operatorSqlMapper.selectAllTheater();
    }

    @Override
    public void registerBoxBasicInfo(RegisterBoxDto registerBoxDto) {
        operatorSqlMapper.insertBox(registerBoxDto);
    }

    @Override
    public void registerBoxDetailInfo(RegisterBoxDto registerBoxDto) {
        for (TypeDto typeDto : registerBoxDto.getTypeDtoList()) {

            BoxTypeDto boxTypeDto = new BoxTypeDto();
            boxTypeDto.setBoxId(registerBoxDto.getId());

            TypeDto searchedTypeDto = operatorSqlMapper.selectTypeDtoByTypeName(typeDto);
            if (searchedTypeDto != null) {
                boxTypeDto.setTypeId(searchedTypeDto.getId());
            } else {
                operatorSqlMapper.insertTypeReturnID(typeDto);
                boxTypeDto.setTypeId(typeDto.getId());
            }
            System.out.println("boxTypeDto = " + boxTypeDto);
            operatorSqlMapper.insertBoxType(boxTypeDto);
        }
    }
}
