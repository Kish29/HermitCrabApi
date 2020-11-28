package com.kish2.hermitcrabapi.controller;

import com.kish2.hermitcrabapi.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("userApi")
public class UserController {

    @Resource
    private IUserService userService;

    @RequestMapping("reg")
    @ResponseBody
    public String regTest() {
        return userService.user_reg();
    }

}
