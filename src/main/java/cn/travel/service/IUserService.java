package cn.travel.service;

import cn.travel.domain.ResultInfo;
import cn.travel.domain.User;

public interface IUserService {
    int registerUser(User user);
    int activateUser(String code);
    User login(User user);
}
