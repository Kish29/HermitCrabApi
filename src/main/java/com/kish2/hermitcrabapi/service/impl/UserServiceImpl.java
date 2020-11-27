package com.kish2.hermitcrabapi.service.impl;

import com.kish2.hermitcrabapi.mapper.IUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl {

    @Resource
    private IUserMapper userMapper;
}
