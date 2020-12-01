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
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserMapper userMapper;

    @Resource
    private IUserBindInfoMapper userBindInfoMapper;

    @Override
    public Map<String, Object> reg(String mobile, String vCode) {
        User user = new User();
        user.setUsername("用户" + mobile);
        user.setRegDate(new Date());
        user.setUserStatus(UserStatus.normal);
        user.setGender(Gender.other);
        if (userMapper.insert(user) > 0) {
            UserBindInfo userBindInfo = new UserBindInfo();
            userBindInfo.setMobile(mobile);
            userBindInfo.setUid(user.getUid());
            userBindInfo.setDepartment("未设置");
            userBindInfo.setGrade(Grade.other);
            userBindInfo.setEmail("未绑定");
            userBindInfo.setStudentId("未绑定");
            userBindInfo.setUserType(UserType.normal);
            userBindInfoMapper.insert(userBindInfo);
            HashMap<String, Object> res = new HashMap<>();
            res.put("user", user);
            res.put("user_bind_info", userBindInfo);
            return res;
        } else
            return null;
    }

    @Override
    public Map<String, Object> authByUsername(String username, String password) {
        return null;
    }
}
