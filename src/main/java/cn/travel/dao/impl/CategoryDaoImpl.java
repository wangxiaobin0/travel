package cn.travel.dao.impl;

import cn.travel.dao.ICategoryDao;
import cn.travel.domain.Category;
import cn.travel.domain.Route;
import cn.travel.util.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDaoImpl implements ICategoryDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());
    @Override
    public List<Category> queryCategory() {
        String sql = "select * from tab_category";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
    }

    @Override
    public Category category(int cid) {
        String sql = "select * from tab_category where cid=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Category.class), cid);
    }
}
