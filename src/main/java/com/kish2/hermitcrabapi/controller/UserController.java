package com.kish2.hermitcrabapi.controller;

import com.kish2.hermitcrabapi.ServerThreadPool;
import com.kish2.hermitcrabapi.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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

    /* 使用future模式，可让主线程进行其他事务的处理，而不阻塞主线程 */
    @RequestMapping("reg")
    @ResponseBody
    public Map<String, Object> reg(String mobile, String vCode) throws ExecutionException, InterruptedException {
        Future<Map<String, Object>> future = ServerThreadPool.THREAD_POOL.submit(() -> {
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
        });
        return future.get();
    }

}
