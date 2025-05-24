package com.toy.toycinema.operator.service;

import com.toy.toycinema.dto.*;


import java.util.List;

public interface OperatorTheaterService {
    void registerTheater(TheaterDto theaterDto);

    List<TheaterDto> findAllTheater();
    void registerBoxBasicInfo(RegisterBoxDto registerBoxDto);
    void registerBoxDetailInfo(RegisterBoxDto registerBoxDto);
}
