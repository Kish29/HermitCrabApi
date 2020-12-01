package com.kish2.hermitcrabapi.service;

import java.util.Map;

public interface IUserService {

    Map<String, Object> reg(String mobile, String vCode);

    Map<String, Object> authByUsername(String username, String password);

}
