package cn.travel.dao.impl;

import cn.travel.dao.IRouteDao;
import cn.travel.domain.Route;
import cn.travel.domain.RouteImg;
import cn.travel.domain.Seller;
import cn.travel.util.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements IRouteDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());

    @Override
    public int queryRouteCount(int cid, String rname) {
        StringBuilder sql = new StringBuilder("select count(cid) from tab_route where 1=1 ");
        List p = new ArrayList();
        if (cid != 0) {
            sql.append(" and cid=? ");
            p.add(cid);
        }
        if (rname != null && rname.length() > 0) {
            sql.append(" and rname like ?");
            p.add("%" + rname + "%");
        }
        return jdbcTemplate.queryForObject(sql.toString(), Integer.class, p.toArray());
    }

    @Override
    public List<Route> queryRoute(int cid, String rname, int turnPageCurrentPage, int turnPageShowNum) {
        StringBuilder sql = new StringBuilder("select * from tab_route where rflag = '1'");
        List p = new ArrayList();
        if (cid != 0) {
            sql.append(" and cid=? ");
            p.add(cid);
        }
        if (rname != null && rname.length() > 0) {
            sql.append(" and rname like ?");
            p.add("%" + rname + "%");
        }
        sql.append(" limit ?,?");
        p.add(turnPageCurrentPage);
        p.add(turnPageShowNum);
        return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<Route>(Route.class), p.toArray());
    }

    @Override
    public Route routeDetail(int rid) {
        String sql = "select * from tab_route where rflag = 1 and rid = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class), rid);
    }

    public List<RouteImg> routeImg(int rid) {
        String sql = "select * from tab_route_img where rid = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(RouteImg.class), rid);
    }

    @Override
    public Seller routeSeller(int sid) {
        String sql = "select * from tab_seller where sid=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Seller.class), sid);
    }
}
