package cn.travel.service.impl;


import cn.travel.dao.IUserDao;
import cn.travel.dao.impl.UserDaoImpl;
import cn.travel.domain.User;
import cn.travel.service.IUserService;
import cn.travel.util.MailUtils;
import cn.travel.util.UuidUtil;

import java.util.UUID;

public class UserServiceImpl implements IUserService {
    IUserDao userDao = new UserDaoImpl();
    @Override
    public int registerUser(User user) {
        //检查用户名是否被占用
        String username = user.getUsername();
        User checkUsername = userDao.checkUsername(username);
        if (checkUsername != null) {
            return 1;
        }
        //生成激活码
        String code = UuidUtil.getUuid();
        user.setCode(code);
        user.setStatus("N");
        //注册用户
        int count = userDao.registerUser(user);
        if (count != 1) {
            return 2;
        }

        //发送激活邮件
        String title = "激活邮件";
        String content = "<a href='http://localhost/travel/user/activate?code=" + code + "'>您正在注册【旅游网】，点击激活。</a>";
        MailUtils.sendMail(user.getEmail(), content, title);
        return  0;
    }

    @Override
    public int activateUser(String code) {
        return userDao.activateUser(code);
    }

    @Override
    public User login(User user) {
        return userDao.login(user.getUsername(), user.getPassword());
    }
}
