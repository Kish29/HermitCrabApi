package com.kish2.hermitcrabapi.controller;

import com.kish2.hermitcrabapi.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("userApi")
public class UserController {

    static final int SERVER_OPERATED_SUCCESS = 111;
    static final int SERVER_OPERATED_FAILURE = 222;
    static final String KEY_SERVER_STATUS = "server_code";
    static final String KEY_SERVER_MSG = "server_msg";

    @Resource
    private IUserService userService;

    // TODO: 2020/11/30 完善返回的键值对象
    private void setRetVal(Map<String, Object> res, int status, int msgIndex) {
        res.put(KEY_SERVER_STATUS, status);
        res.put(KEY_SERVER_MSG, "欢迎小贝壳的到来~");
    }

    @RequestMapping("reg")
    @ResponseBody
    public Map<String, Object> reg(String mobile, String vCode) {
        // TODO: 2020/11/30 手机号有效性再次验证
        // TODO: 2020/11/30 验证码有效性验证
        Map<String, Object> reg = userService.reg(mobile, vCode);
        if (reg != null) {
            reg.put(KEY_SERVER_STATUS, SERVER_OPERATED_SUCCESS);
            reg.put(KEY_SERVER_MSG, "欢迎小贝壳的到来~");
        } else {
            reg = new HashMap<>();
            reg.put(KEY_SERVER_STATUS, SERVER_OPERATED_FAILURE);
            reg.put(KEY_SERVER_MSG, "服务器异常，请稍后再试试吧~");
        }
        return reg;
    }

}
