package cn.travel.web.servlet.old;

import cn.travel.domain.ResultInfo;
import cn.travel.service.IUserService;
import cn.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/activateUser")
public class ActivateUserFilterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        IUserService userService = new UserServiceImpl();
        ResultInfo resultInfo = new ResultInfo();
        int i = userService.activateUser(code);
        if (i == 1) {
            response.setContentType("text/html;charset=utf-8");
            response.sendRedirect(request.getContextPath() + "/activate.html");
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("注册失败");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
