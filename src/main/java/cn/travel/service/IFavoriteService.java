package cn.travel.service;

import cn.travel.domain.Favorite;
import cn.travel.domain.Route;
import cn.travel.domain.TurnPage;

import java.util.List;

public interface IFavoriteService {
    TurnPage<Route> myFavorite(int uid, int turnPageCurrentPage, int turnPageShowNum);
    void addFavorite(int uid, int rid);
    void cancelFavorite(int uid, int rid);
    boolean isFavorite(int uid, int rid);
}
