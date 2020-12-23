package com.kish2.hermitcrabapi.controller;

import com.kish2.hermitcrabapi.ServerThreadPool;
import com.kish2.hermitcrabapi.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


@Controller
@RequestMapping("userApi")
public class UserController {

    @Resource
    private IUserService userService;

    /* 模拟向手机发送验证码 */
    @RequestMapping("verifyCode")
    @ResponseBody
    public void verifyCode(String mobile) throws ExecutionException, InterruptedException {
        ServerThreadPool.THREAD_POOL.execute(() -> {
            String mobileCode = userService.getMobileCode(mobile);
            // TODO: 2020/12/23 向手机发送验证码
            /* sendVerifyCode(mobile) */
        });
    }

    /* 使用future模式，可让主线程进行其他事务的处理，而不阻塞主线程 */
    @RequestMapping("reg")
    @ResponseBody
    public Map<String, Object> reg(String mobile, String vCode) throws ExecutionException, InterruptedException {
        Future<Map<String, Object>> future = ServerThreadPool.THREAD_POOL.submit(() -> {
            return userService.reg(mobile, vCode.trim());   // 注意剔除多余空格
        });
        return future.get();
    }

    @RequestMapping("authentication/usrnm_psswd")
    @ResponseBody
    public Map<String, Object> authByUsername(String username, String password) throws ExecutionException, InterruptedException {
        Future<Map<String, Object>> future = ServerThreadPool.THREAD_POOL.submit(() -> {
            return userService.authByUsername(username.trim(), password.trim());   // 注意剔除多余空格
        });
        return future.get();
    }
}
