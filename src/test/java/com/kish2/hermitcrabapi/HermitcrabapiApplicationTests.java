package com.kish2.hermitcrabapi;

import com.kish2.hermitcrabapi.controller.UserController;
import com.kish2.hermitcrabapi.service.IUserService;
import com.kish2.hermitcrabapi.utils.ValidCheck;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
class HermitcrabapiApplicationTests {

    @Resource
    private IUserService userService;
    @Resource
    private UserController userController;

    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        System.out.println("这是中文");
    }

    /* 手机号重复性检查 */
    /* pass */
    @Test
    void mobileRepeatCheck() {
        for (int i = 0; i < 10; i++) {
            System.out.println(userService.mobileRepeatCheck("13658398508"));
        }
    }

    @Test
    void concurrentAndSingle() {
        regConcurrent();
        regSingle();
    }

    /* 注册并发测试 */
    @Test
    void regConcurrent() {
        long start, end;
        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            int finalI = i;
            ServerThreadPool.THREAD_POOL.execute(() -> userService.reg(String.valueOf(finalI), null));
        }
        while (true) {
            if (ServerThreadPool.THREAD_POOL.getActiveCount() == 0)
                break;
        }
        end = System.currentTimeMillis();
        System.out.println((end - start) + "ms");
    }

    /* 并发下，流程比非并发快7倍 */

    /* 主线程注册测试 */
    @Test
    void regSingle() {
        long start, end;
        start = System.currentTimeMillis();
        for (int i = 10001; i < 20001; i++) {
            userService.reg(String.valueOf(i), null);
        }
        end = System.currentTimeMillis();
        System.out.println((end - start) + "ms");
    }

    /* controller的并发测试 */
    @Test
    void controllerConcurrentTest() throws ExecutionException, InterruptedException {
        long start, end;
        start = System.currentTimeMillis();
        for (int i = 50003; i <= 50010; i++) {
            Map<String, Object> reg = userController.reg(String.valueOf(i), null);
            System.out.println(reg);
        }
        while (true) {
            if (ServerThreadPool.THREAD_POOL.getActiveCount() == 0)
                break;
        }
        end = System.currentTimeMillis();
        System.out.println("Concurrent use " + (end - start) + "ms");
        /* 7456ms = 7.4s */
    }

    /* controller的非并发测试 */
    @Test
    void controllerSingleTest() throws ExecutionException, InterruptedException {
        long start, end;
        start = System.currentTimeMillis();
        for (int i = 10001; i <= 20001; i++) {
            Map<String, Object> reg = userController.reg(String.valueOf(i), null);
//            System.out.println(reg);
        }
        end = System.currentTimeMillis();
        System.out.println("Single use " + (end - start) + "ms");
        /* 48311ms  = 48.3s */
    }

    /* pass */
    @Test
    void randomTest() {
        for (int i = 0; i < 10; i++) {
            System.out.println(ValidCheck.getRandomCode(6));
        }
    }

    /* 用户登录测试 */
    /* pass */
    @Test
    void loginTest() throws ExecutionException, InterruptedException {
        for (int i = 1; i <= 10; i++) {
            System.out.println(userController.authByUsername("用户" + i, "123456"));
        }
    }

}
