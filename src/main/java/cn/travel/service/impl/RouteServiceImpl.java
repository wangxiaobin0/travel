package cn.travel.service.impl;

import cn.travel.dao.ICategoryDao;
import cn.travel.dao.IRouteDao;
import cn.travel.dao.impl.CategoryDaoImpl;
import cn.travel.dao.impl.RouteDaoImpl;
import cn.travel.domain.*;
import cn.travel.service.IRouteService;

import java.util.List;

public class RouteServiceImpl implements IRouteService {
    private IRouteDao routeDao = new RouteDaoImpl();
    private ICategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public TurnPage<Route> queryRoute(int cid, String rname, int turnPageCurrentPage, int turnPageShowNum) {
        int turnPageTotalNum = routeDao.queryRouteCount(cid, rname);
        int turnPageTotalPage = turnPageTotalNum % turnPageShowNum == 0 ? turnPageTotalNum / turnPageShowNum : (turnPageTotalNum / turnPageShowNum) + 1;
        TurnPage<Route> routeTurnPage = new TurnPage<Route>();
        routeTurnPage.setTurnPageCurrentPage(String.valueOf(turnPageCurrentPage));
        routeTurnPage.setTurnPageShowNum(String.valueOf(turnPageShowNum));
        routeTurnPage.setTurnPageTotalNum(String.valueOf(turnPageTotalNum));
        routeTurnPage.setTurnPageTotalPage(String.valueOf(turnPageTotalPage));
        routeTurnPage.setList(routeDao.queryRoute(cid, rname, (turnPageCurrentPage - 1) * turnPageShowNum, turnPageShowNum));
        return routeTurnPage;
    }

    @Override
    public Route routeDetail(int rid) {
        Route routeDetail = routeDao.routeDetail(rid);

        List<RouteImg> routeImg = routeDao.routeImg(rid);
        Seller seller = routeDao.routeSeller(routeDetail.getSid());
        Category category = categoryDao.category(routeDetail.getCid());
        routeDetail.setRouteImgList(routeImg);
        routeDetail.setSeller(seller);
        routeDetail.setCategory(category);

        return routeDetail;
    }
}
