package cn.travel.web.servlet;

import cn.travel.domain.Category;
import cn.travel.domain.ResultInfo;
import cn.travel.domain.Route;
import cn.travel.service.ICategoryService;
import cn.travel.service.impl.CategoryServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    ICategoryService  categoryService = new CategoryServiceImpl();
    ResultInfo resultInfo;
    public void queryCategory(HttpServletRequest request, HttpServletResponse response) throws Exception{
        List<Category> categoryList = null;
        try {
            categoryList = categoryService.queryCategory();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultInfo = new ResultInfo("0000", "", categoryList);
            writeValue(resultInfo, response);
        }
    }
}
