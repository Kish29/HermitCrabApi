package com.kish2.hermitcrabapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kish2.hermitcrabapi.bean.User;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserMapper extends BaseMapper<User> {

    /* 修改用户名 */
    @Update("update user set username=#{username} where uid=#{uid}")
    void updateUsername(long uid, String username);

    /* 修改密码 */
    @Update("update user set password=#{password} where uid=#{uid}")
    void updatePassword(long uid, String password);
}

