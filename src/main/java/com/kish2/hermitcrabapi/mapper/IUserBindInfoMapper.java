package com.kish2.hermitcrabapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kish2.hermitcrabapi.bean.UserBindInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IUserBindInfoMapper extends BaseMapper<UserBindInfo> {

    /* 手机号码重复性检查 */
    @Select("select count(*) from user_bind_info where mobile={mobile}")
    int mobileRepeatCheck(String mobile);
}
