package cn.travel.service.impl;

import cn.travel.dao.IFavoriteDao;
import cn.travel.dao.impl.FavoriteDaoImpl;
import cn.travel.domain.Favorite;
import cn.travel.domain.Route;
import cn.travel.domain.TurnPage;
import cn.travel.service.IFavoriteService;

import java.util.List;

public class FavoriteServiceImpl implements IFavoriteService {
    private IFavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public TurnPage<Route> myFavorite(int uid, int turnPageCurrentPage, int turnPageShowNum) {
        TurnPage<Route> favoriteTurnPage = new TurnPage<Route>();
        Favorite myFavorite = new Favorite();
        int favoriteCount = favoriteDao.myFavoriteCount(uid);
        List<Route> routeList = favoriteDao.myFavorite(uid, (turnPageCurrentPage - 1) * turnPageShowNum, turnPageShowNum);
        myFavorite.setUid(uid);
        myFavorite.setFavoriteCount(favoriteCount);
        myFavorite.setRouteList(routeList);

        favoriteTurnPage.setTurnPageCurrentPage(String.valueOf(turnPageCurrentPage));
        favoriteTurnPage.setTurnPageShowNum(String.valueOf(turnPageShowNum));
        favoriteTurnPage.setTurnPageTotalNum(String.valueOf(favoriteCount));
        favoriteTurnPage.setTurnPageTotalPage(String.valueOf((favoriteCount / turnPageShowNum) + 1));
        favoriteTurnPage.setList(routeList);
        return favoriteTurnPage;
    }

    @Override
    public void addFavorite(int uid, int rid) {
        favoriteDao.addFavorite(uid, rid);
    }

    @Override
    public void cancelFavorite(int uid, int rid) {
        favoriteDao.cancelFavorite(uid, rid);
    }

    @Override
    public boolean isFavorite(int uid, int rid) {

        return favoriteDao.isFavorite(uid, rid) != 0;
    }
}
