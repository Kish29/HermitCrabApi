package com.kish2.hermitcrabapi.enums.user;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {

    other(0, "未设置"),
    man(1, "男"),
    woman(2, "女");

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

    Gender(int key, String val) {
        this.key = key;
        this.val = val;
    }
}
