package com.kish2.hermitcrabapi.service.impl;

import com.kish2.hermitcrabapi.bean.User;
import com.kish2.hermitcrabapi.bean.UserBindInfo;
import com.kish2.hermitcrabapi.enums.user.Gender;
import com.kish2.hermitcrabapi.enums.user.Grade;
import com.kish2.hermitcrabapi.enums.user.UserStatus;
import com.kish2.hermitcrabapi.enums.user.UserType;
import com.kish2.hermitcrabapi.mapper.IUserBindInfoMapper;
import com.kish2.hermitcrabapi.mapper.IUserMapper;
import com.kish2.hermitcrabapi.service.IUserService;
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
        // TODO: 2020/11/30 手机号有效性再次验证
            // if (mobileRepeatCheck(mobile)) {
        //     return null;
        // }
        // // TODO: 2020/11/30 验证码有效性验证
        // if (!mobileAndCodeCheck(mobile, vCode)) {
        //     return null;
        // }
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
            HashMap<String, Object> res = new HashMap<>();
            res.put("user", user);
            res.put("user_bind_info", userBindInfo);
            return res;
        } else {
            return null;
        }
    }

    @Override
    public Map<String, Object> authByUsername(String username, String password) {
        return null;
    }

    @Override
    public boolean mobileAndCodeCheck(String mobile, String vCode) {
        String key = mobileCode.get(mobile);
        /* 移除数据 */
        mobileCode.remove(mobile);
        return key != null && key.equals(vCode);
    }

    @Override
    public String getMobileCode(String mobile, HashMap<String, String> mobileCode) {
        return null;
    }

    @Override
    public boolean mobileRepeatCheck(String mobile) {
        if (!ValidCheck.isValidMobile(mobile))
            return false;
        return userBindInfoMapper.mobileRepeatCheck(mobile) <= 0;
    }
}
