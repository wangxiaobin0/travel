package cn.travel.web.servlet.old;

import cn.travel.domain.ResultInfo;
import cn.travel.domain.User;
import cn.travel.service.IUserService;
import cn.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        IUserService userService = new UserServiceImpl();
        User session_user = null;
        ResultInfo resultInfo = null;
        String errorCode = "0000";
        try {
            //验证图形验证码
            String session_checkCode = (String) request.getSession().getAttribute("session_checkCode");
            String checkCode = request.getParameter("checkCode");

            if (session_checkCode == null || "".equals(session_checkCode)) {
                resultInfo = new ResultInfo("0006", "请刷新页面后重试", null);
                throw new Exception("请刷新页面后重试");
            }
            if (!session_checkCode.equalsIgnoreCase(checkCode)) {
                resultInfo = new ResultInfo("0001", "验证码错误", null);
                throw new Exception("验证码错误");
            }
            //封装User对象
            User user = new User();
            Map<String, String[]> map = request.getParameterMap();
            BeanUtils.populate(user, map);
            session_user = userService.login(user);
            if (session_user == null) {
                resultInfo = new ResultInfo("0004", "用户名或密码错误", null);
                throw new Exception("用户名或密码错误");
            } else if (session_user != null && !"Y".equals(session_user.getStatus())) {
                resultInfo = new ResultInfo("0005", "该用户未激活,请前往邮箱激活", null);
                throw new Exception("该用户未激活,请前往邮箱激活");
            } else {
                request.getSession().setAttribute("session_user", session_user);
                resultInfo = new ResultInfo("0000", "", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response.setContentType("application/json;charset=utf-8");
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getWriter(), resultInfo);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
