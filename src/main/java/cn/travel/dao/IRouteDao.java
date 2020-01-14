package cn.travel.dao;

import cn.travel.domain.Category;
import cn.travel.domain.Route;
import cn.travel.domain.RouteImg;
import cn.travel.domain.Seller;

import java.util.List;

public interface IRouteDao {
    int queryRouteCount(int cid, String rname);
    List<Route> queryRoute(int cid, String rname, int turnPageCurrentPage, int turnPageShowNum);
    Route routeDetail(int rid);
    Seller routeSeller(int sid);
    List<RouteImg> routeImg(int rid);
}
