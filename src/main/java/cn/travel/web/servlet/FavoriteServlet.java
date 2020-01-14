package cn.travel.web.servlet;

import cn.travel.domain.*;
import cn.travel.service.IFavoriteService;
import cn.travel.service.impl.FavoriteServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/favorite/*")
public class FavoriteServlet extends BaseServlet {
    private IFavoriteService favoriteService = new FavoriteServiceImpl();
    private ResultInfo resultInfo;
    public void myFavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TurnPage<Route> myFavorite = null;
        try {
            checkSession(request, response);
            int uid = Integer.parseInt(request.getParameter("uid"));
            int turnPageCurrentPage = Integer.parseInt(request.getParameter("turnPageCurrentPage"));
                int turnPageShowNum = Integer.parseInt(request.getParameter("turnPageShowNum"));
            myFavorite = favoriteService.myFavorite(uid, turnPageCurrentPage, turnPageShowNum);
        } catch (Exception e) {

        } finally {
            resultInfo = new ResultInfo("0000","",myFavorite);
            writeValue(resultInfo,response);
        }
    }
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User session_user = (User) request.getSession().getAttribute("session_user");
        if (session_user == null) {
            resultInfo = new ResultInfo("0004", "请先登录", null);
            writeValue(resultInfo, response);
            return;
        }
        int uid = session_user.getUid();
        int rid = Integer.parseInt(request.getParameter("rid"));
        favoriteService.addFavorite(uid, rid);
        resultInfo = new ResultInfo("0000", "", null);
        writeValue(resultInfo, response);
    }
    public void cancelFavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User session_user = (User) request.getSession().getAttribute("session_user");
        if (session_user == null) {
            resultInfo = new ResultInfo("0004", "请先登录", null);
            writeValue(resultInfo, response);
            return;
        }
        int uid = session_user.getUid();
        int rid = Integer.parseInt(request.getParameter("rid"));
        favoriteService.cancelFavorite(uid, rid);
        resultInfo = new ResultInfo("0000", "", null);
        writeValue(resultInfo, response);
    }

    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User session_user = (User) request.getSession().getAttribute("session_user");
        if (session_user == null) {
            resultInfo = new ResultInfo("0004", "请先登录", null);
            writeValue(resultInfo, response);
            return;
        }
        int uid = session_user.getUid();
        int rid = Integer.parseInt(request.getParameter("rid"));
        boolean favorite = favoriteService.isFavorite(uid, rid);
        if (favorite) {
            resultInfo = new ResultInfo("0000", "", null);
        } else {
            resultInfo = new ResultInfo("0005", "", null);
        }
        writeValue(resultInfo, response);
    }


    public void checkSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User session_user = (User) session.getAttribute("session_user");
        int uid = Integer.parseInt(request.getParameter("uid"));
        if (session_user == null || session_user.getUid() != uid) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("请重新登录");
        }
    }
}
