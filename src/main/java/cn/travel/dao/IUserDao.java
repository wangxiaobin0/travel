package cn.travel.dao;

import cn.travel.domain.ResultInfo;
import cn.travel.domain.User;

public interface IUserDao {
    User checkUsername(String username);
    int registerUser(User user);
    int activateUser(String code);
    User login(String name, String password);
}
