package cn.travel.dao;

import cn.travel.domain.Category;
import cn.travel.domain.Route;

import java.util.List;

public interface ICategoryDao {
    List<Category> queryCategory();
    Category category(int cid);
}
