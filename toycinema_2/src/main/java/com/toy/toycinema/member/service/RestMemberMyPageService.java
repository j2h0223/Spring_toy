package com.toy.toycinema.member.service;


import com.toy.toycinema.dto.FilmReservationDto;
import com.toy.toycinema.dto.MemberDto;

import java.util.List;
import java.util.Map;

public interface RestMemberMyPageService {

    List<Map<String, Object>> getReservationInfo(MemberDto memberDto);
    List<Map<String, Object>> getAlreadyViewInfo(MemberDto memberDto);
    List<Map<String, Object>> getReviewedInfo(MemberDto memberDto);

    Map<String, Object> getPaymentCheck(FilmReservationDto filmReservationDto);
    void removeReservation(FilmReservationDto filmReservationDto);

}
