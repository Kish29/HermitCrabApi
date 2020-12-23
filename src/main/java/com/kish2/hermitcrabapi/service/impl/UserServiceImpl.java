package com.kish2.hermitcrabapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kish2.hermitcrabapi.bean.User;
import com.kish2.hermitcrabapi.bean.UserBindInfo;
import com.kish2.hermitcrabapi.enums.user.Gender;
import com.kish2.hermitcrabapi.enums.user.Grade;
import com.kish2.hermitcrabapi.enums.user.UserStatus;
import com.kish2.hermitcrabapi.enums.user.UserType;
import com.kish2.hermitcrabapi.mapper.IUserBindInfoMapper;
import com.kish2.hermitcrabapi.mapper.IUserMapper;
import com.kish2.hermitcrabapi.service.IUserService;
import com.kish2.hermitcrabapi.utils.LicenseCheckUtil;
import com.kish2.hermitcrabapi.utils.ServerStatus;
import com.kish2.hermitcrabapi.utils.ValidCheck;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    /* 验证码保存HashMap */
    private static final HashMap<String, String> mobileCode = new HashMap<>();

    @Resource
    private IUserMapper userMapper;

    @Resource
    private IUserBindInfoMapper userBindInfoMapper;

    @Override
    public Map<String, Object> reg(String mobile, String vCode) {
        HashMap<String, Object> res = new HashMap<>();
        /* 手机号有效性和重复性验证 */
        if (!ValidCheck.isValidMobile(mobile)) {
            res.put(ServerStatus.KEY_SERVER_STATUS, ServerStatus.SERVER_OPERATED_FAILURE);
            res.put(ServerStatus.KEY_SERVER_MSG, "无效的手机号");
            return res;
        }
        if (mobileRepeatCheck(mobile)) {
            res.put(ServerStatus.KEY_SERVER_STATUS, ServerStatus.SERVER_OPERATED_FAILURE);
            res.put(ServerStatus.KEY_SERVER_MSG, "该手机号已被注册");
            return res;
        }
        /* 验证码有效性验证 */
        if (!mobileAndCodeCheck(mobile, vCode)) {
            res.put(ServerStatus.KEY_SERVER_STATUS, ServerStatus.SERVER_OPERATED_FAILURE);
            res.put(ServerStatus.KEY_SERVER_MSG, "验证码错误");
            return res;
        }
        User user = new User();
        user.setUsername("用户" + mobile);
        user.setRegDate(new Date());
        user.setUserStatus(UserStatus.normal);
        user.setGender(Gender.other);
        if (userMapper.insert(user) > 0) {
            UserBindInfo userBindInfo = new UserBindInfo();
            userBindInfo.setMobile(mobile);
            userBindInfo.setUid(user.getUid());
            userBindInfo.setGrade(Grade.other);
            userBindInfo.setUserType(UserType.normal);
            userBindInfoMapper.insert(userBindInfo);
            res.put("user", user);
            res.put("user_bind_info", userBindInfo);
            res.put(ServerStatus.KEY_SERVER_STATUS, ServerStatus.SERVER_OPERATED_SUCCESS);
            res.put(ServerStatus.KEY_SERVER_MSG, "欢迎小贝壳的到来~");
        } else {
            res.put(ServerStatus.KEY_SERVER_STATUS, ServerStatus.SERVER_OPERATED_FAILURE);
            res.put(ServerStatus.KEY_SERVER_MSG, "服务器异常，请稍后再试试吧~");
        }
        return res;
    }

    @Override
    public Map<String, Object> authByUsername(String username, String password) {
        HashMap<String, Object> res = new HashMap<>();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        password = LicenseCheckUtil.passwordEncryption(password);
        queryWrapper.eq("username", username).eq("password", password);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            res.put(ServerStatus.KEY_SERVER_STATUS, ServerStatus.SERVER_OPERATED_FAILURE);
            res.put(ServerStatus.KEY_SERVER_MSG, "密码错误");
            return res;
        }
        UserBindInfo userBindInfo = userBindInfoMapper.selectById(user.getUid());
        if (userBindInfo == null) {
            res.put(ServerStatus.KEY_SERVER_STATUS, ServerStatus.SERVER_OPERATED_FAILURE);
            res.put(ServerStatus.KEY_SERVER_MSG, "服务器异常，请稍后再试试吧~");
            return res;
        }
        res.put(ServerStatus.KEY_SERVER_STATUS, ServerStatus.SERVER_OPERATED_SUCCESS);
        res.put(ServerStatus.KEY_SERVER_MSG, "欢迎回来~" + user.getUsername());
        res.put("user", user);
        res.put("user_bind_info", userBindInfo);
        return res;
    }

    @Override
    public boolean mobileAndCodeCheck(String mobile, String vCode) {
        String key = mobileCode.get(mobile);
        /* 移除数据 */
        mobileCode.remove(mobile);
        return key != null && key.equals(vCode);
    }

    @Override
    public String getMobileCode(String mobile) {
        String code = ValidCheck.getRandomCode(6);
        mobileCode.put(mobile, code);
        return code;
    }

    @Override
    public boolean mobileRepeatCheck(String mobile) {
        return userBindInfoMapper.mobileRepeatCheck(mobile) > 0;
    }
}
