package cn.travel.service;

import cn.travel.domain.Category;
import cn.travel.domain.Route;

import java.util.List;

public interface ICategoryService {
    List<Category> queryCategory();
}
