package com.toy.toycinema.operator.service;

import com.toy.toycinema.dto.*;
import com.toy.toycinema.operator.mapper.OperatorSqlMapper;
import org.springframework.stereotype.Service;

@Service
public class OperatorAuthServiceImpl implements OperatorAuthService{
    private final OperatorSqlMapper operatorSqlMapper;

    public OperatorAuthServiceImpl(OperatorSqlMapper operatorSqlMapper) {
        this.operatorSqlMapper = operatorSqlMapper;
    }

    @Override
    public void registerOperator(OperatorDto operatorDto) {
        operatorSqlMapper.insertOperator(operatorDto);
    }

    @Override
    public OperatorDto loginOperator(OperatorDto operatorDto) {
        return operatorSqlMapper.selectOperatorForAuth(operatorDto);
    }
}
