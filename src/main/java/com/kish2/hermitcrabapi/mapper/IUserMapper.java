package com.kish2.hermitcrabapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kish2.hermitcrabapi.bean.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserMapper extends BaseMapper<User> {

    @Select("select * from ")
    void selectById(long uid);
}
