package cn.travel.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 收藏实体类
 */
public class Favorite implements Serializable {
    private int uid;
    private List<Route> routeList;
    private int favoriteCount;

    public Favorite() {
    }

    public Favorite(int uid, List<Route> routeList, int favoriteCount) {
        this.uid = uid;
        this.routeList = routeList;
        this.favoriteCount = favoriteCount;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public List<Route> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<Route> routeList) {
        this.routeList = routeList;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }
}
