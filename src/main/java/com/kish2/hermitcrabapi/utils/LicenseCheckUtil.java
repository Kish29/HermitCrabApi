package com.kish2.hermitcrabapi.utils;

import cn.hutool.crypto.SecureUtil;

public class LicenseCheckUtil {

    public static String passwordEncryption(String origin) {
        return SecureUtil.sha256(SecureUtil.md5(origin));
    }
}
