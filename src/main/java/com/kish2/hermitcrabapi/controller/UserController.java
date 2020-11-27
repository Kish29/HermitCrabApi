package com.kish2.hermitcrabapi.controller;

import com.kish2.hermitcrabapi.service.IUserService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class UserController {

    @Resource
    private IUserService userService;
}
