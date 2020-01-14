package cn.travel.service;

import cn.travel.domain.Category;
import cn.travel.domain.Route;
import cn.travel.domain.TurnPage;

import java.util.List;

public interface IRouteService {
    TurnPage<Route> queryRoute(int cid, String rname, int turnPageCurrentPage, int turnPageShowNum);
    Route routeDetail(int rid);
}
