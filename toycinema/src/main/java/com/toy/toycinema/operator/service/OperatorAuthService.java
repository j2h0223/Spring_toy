package com.toy.toycinema.operator.service;

import com.toy.toycinema.dto.*;

public interface OperatorAuthService {
    void registerOperator(OperatorDto operatorDto);

    OperatorDto loginOperator(OperatorDto operatorDto);
}
