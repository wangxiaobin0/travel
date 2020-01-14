package cn.travel.service.impl;

import cn.travel.dao.ICategoryDao;
import cn.travel.dao.impl.CategoryDaoImpl;
import cn.travel.domain.Category;
import cn.travel.domain.Route;
import cn.travel.service.ICategoryService;
import cn.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategoryServiceImpl implements ICategoryService {
    ICategoryDao categoryDao = new CategoryDaoImpl();
    Jedis jedis = JedisUtil.getJedis();
    @Override
    public List<Category> queryCategory() {
        List<Category> categoryList = new ArrayList<>();
        Map<String, String> category = jedis.hgetAll("category");
        if (!category.isEmpty()) {
            for (Map.Entry<String, String> e : category.entrySet()) {
                categoryList.add(new Category(Integer.parseInt(e.getKey()), e.getValue()));
            }
            System.out.println("redis查询种类");
        } else {
            categoryList = categoryDao.queryCategory();
            jedis.del("category");
            for (Category c : categoryList) {
                jedis.hset("category", String.valueOf(c.getCid()), c.getCname());
            }
            System.out.println("数据查询种类");
        }
        return categoryList;
    }
}
