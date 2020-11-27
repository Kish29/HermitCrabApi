package com.kish2.hermitcrabapi.enums.user;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserType {

    normal(0, "普通用户"),
    student(1, "学生"),
    teacher(2, "教师"),
    creator(3, "创作者"),
    admin(4, "管理员"),
    other(5, "其他");

    @EnumValue
    private final int key;
    @JsonValue
    private final String val;

    public int getKey() {
        return key;
    }

    public String getVal() {
        return val;
    }

    UserType(int key, String val) {
        this.key = key;
        this.val = val;
    }
}
