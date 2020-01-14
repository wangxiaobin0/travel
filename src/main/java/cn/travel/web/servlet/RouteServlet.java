package cn.travel.web.servlet;

import cn.travel.domain.Category;
import cn.travel.domain.ResultInfo;
import cn.travel.domain.Route;
import cn.travel.domain.TurnPage;
import cn.travel.service.ICategoryService;
import cn.travel.service.IRouteService;
import cn.travel.service.impl.CategoryServiceImpl;
import cn.travel.service.impl.RouteServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    IRouteService routeService = new RouteServiceImpl();
    ResultInfo resultInfo;

    public void queryRoute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        TurnPage<Route> routeTurnPage = null;
        try {
            int cid = Integer.parseInt(request.getParameter("cid"));
            String rname = request.getParameter("rname");
            int turnPageCurrentPage = Integer.parseInt(request.getParameter("turnPageCurrentPage"));
            int turnPageShowNum = Integer.parseInt(request.getParameter("turnPageShowNum"));
            routeTurnPage = routeService.queryRoute(cid, rname, turnPageCurrentPage, turnPageShowNum);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultInfo = new ResultInfo("0000", "", routeTurnPage);
            writeValue(resultInfo, response);
        }
    }

    public void routeDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Route routeDetail = null;
        try {
            int rid = Integer.parseInt(request.getParameter("rid"));
            routeDetail = routeService.routeDetail(rid);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultInfo = new ResultInfo("0000", "", routeDetail);
            writeValue(resultInfo, response);
        }
    }
}
