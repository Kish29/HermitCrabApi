package com.kish2.hermitcrabapi.enums.user;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserStatus {

    normal(0, "账号正常"),
    banning(1, "账号封禁中");

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

    UserStatus(int key, String val) {
        this.key = key;
        this.val = val;
    }
}
