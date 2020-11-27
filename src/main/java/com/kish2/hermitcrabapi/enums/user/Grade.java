package com.kish2.hermitcrabapi.enums.user;

public enum Grade {

    other(0, "其他"),
    grade1(1, "大一"),
    grade2(2, "大二"),
    grade3(3, "大三"),
    grade4(4, "大四"),
    postgraduate(5, ""),
    doctor(6, "大四");

    private final int key;
    private final String val;

    Grade(int key, String val) {
        this.key = key;
        this.val = val;
    }

    public int getKey() {
        return key;
    }

    public String getVal() {
        return val;
    }
}
