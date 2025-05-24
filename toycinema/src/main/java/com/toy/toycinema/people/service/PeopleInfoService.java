package com.toy.toycinema.people.service;


import com.toy.toycinema.dto.*;

import java.util.Map;

public interface PeopleInfoService {
    Map<String, Object> getPeopleFilm(PeopleDto param);
}
