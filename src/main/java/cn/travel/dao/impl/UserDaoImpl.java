package cn.travel.dao.impl;

import cn.travel.dao.IUserDao;
import cn.travel.domain.User;
import cn.travel.util.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements IUserDao {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());
    @Override
    public User checkUsername(String username) {
        String sql = "select * from tab_user where username=?";

        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
        } catch (Exception e) {

        }
        return user;
    }

    @Override
    public int registerUser(User user) {
        try {
            String sql = "insert into tab_user(username, password, email, name, telephone, sex, birthday, status, code) values(?,?,?,?,?,?,?,?,?)";
            String username = user.getUsername();
            String password = user.getPassword();
            String email = user.getEmail();
            String name = user.getName();
            String telephone = user.getTelephone();
            String sex = user.getSex();
            String birthday = user.getBirthday();
            String status = user.getStatus();
            String code = user.getCode();

            return jdbcTemplate.update(sql, username, password, email, name, telephone, sex, birthday, status, code);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int activateUser(String code) {
        String sql = "update tab_user set status = 'Y', code = '' where status = 'N' and code=?";
        return jdbcTemplate.update(sql, code);
    }

    @Override
    public User login(String name, String password) {
        String sql = "select * from tab_user where username=? and password=?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), name, password);
        } catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
}
