package cn.travel.dao;

import cn.travel.domain.Favorite;
import cn.travel.domain.Route;

import java.util.List;

public interface IFavoriteDao {
    List<Route> myFavorite(int uid, int turnPageCurrentPage, int turnPageShowNum);
    int myFavoriteCount(int uid);
    void addFavorite(int uid, int rid);
    void cancelFavorite(int uid, int rid);
    int isFavorite(int uid, int rid);
}
