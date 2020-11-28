package com.kish2.hermitcrabapi.service.impl;

import com.kish2.hermitcrabapi.bean.User;
import com.kish2.hermitcrabapi.mapper.IUserMapper;
import com.kish2.hermitcrabapi.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserMapper userMapper;

    @Override
    public String user_reg() {
        User user = userMapper.selectById(12);
        if (user == null)
            return "空的 傻逼！";
        else return user.toString();
    }
}
