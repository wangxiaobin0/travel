package cn.travel.dao.impl;

import cn.travel.dao.IFavoriteDao;
import cn.travel.domain.Favorite;
import cn.travel.domain.Route;
import cn.travel.util.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class FavoriteDaoImpl implements IFavoriteDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());
    @Override
    public List<Route> myFavorite(int uid, int turnPageCurrentPage, int turnPageShowNum) {
        String sql = "select * from tab_favorite a, tab_route b where a.rid=b.rid and a.uid=? limit ?,?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Route.class), uid, turnPageCurrentPage, turnPageShowNum);
    }

    @Override
    public int myFavoriteCount(int uid) {
        String sql = "select count(rid) from tab_favorite where uid = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, uid);
    }

    @Override
    public void addFavorite(int uid, int rid) {
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(false);
            LocalDate date = LocalDate.now();
            String sql = "insert into tab_favorite values(?,?,?)";
            jdbcTemplate.update(sql, rid, date, uid);
            String sql2 =  "update tab_route set count = count + 1 where rid=?";
            jdbcTemplate.update(sql2, rid);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            JDBCUtil.close(connection ,null);
        }
    }

    @Override
    public void cancelFavorite(int uid, int rid) {
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(false);
            String sql = "delete from tab_favorite where rid=? and uid=?";
            jdbcTemplate.update(sql, rid, uid);
            String sql2 =  "update tab_route set count = count - 1 where rid=?";
            jdbcTemplate.update(sql2, rid);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            JDBCUtil.close(connection ,null);
        }
    }

    @Override
    public int isFavorite(int uid, int rid) {
        String sql = "select count(*) from tab_favorite where uid =? and rid =?";
        int count = 0;
        try {
            count = jdbcTemplate.queryForObject(sql, Integer.class, uid, rid);
        }  catch (Exception e) {
            count = 0;
        }
        return count;
    }
}
