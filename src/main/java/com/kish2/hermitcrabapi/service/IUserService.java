package com.kish2.hermitcrabapi.service;

import java.util.HashMap;
import java.util.Map;

public interface IUserService {

    Map<String, Object> reg(String mobile, String vCode);

    Map<String, Object> authByUsername(String username, String password);

    /* 手机号与验证码检查 */
    boolean mobileAndCodeCheck(String mobile, String vCode);

    /* 获取验证码
     * 并存入到容器内 */
    String getMobileCode(String mobile, HashMap<String, String> mobileCode);


    /* 手机号有效性和重复性检查 */
    boolean mobileRepeatCheck(String mobile);

}
