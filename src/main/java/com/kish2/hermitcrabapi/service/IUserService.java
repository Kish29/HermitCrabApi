package com.kish2.hermitcrabapi.service;

import java.util.Map;

public interface IUserService {

    Map<String, Object> reg(String mobile, String code);

    Map<String, Object> authByUsername(String username, String password);

    Map<String, Object> authByMobile(String mobile, String code);

    /* token验证登录方式 */
    Map<String, Object> authByToken(String token);

    Map<String, Object> updateUsername(long uid, String username);

    Map<String, Object> updatePassword(long uid, String password);

    /* 手机号与验证码检查 */
    boolean mobileAndCodeCheck(String mobile, String code);

    /* 获取验证码
     * 并存入到容器内 */
    String getMobileCode(String mobile);

    /* 手机号有效性和重复性检查 */
    boolean mobileRepeatCheck(String mobile);

}
