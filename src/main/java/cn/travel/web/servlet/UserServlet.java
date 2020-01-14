package cn.travel.web.servlet;

import cn.travel.domain.ResultInfo;
import cn.travel.domain.User;
import cn.travel.service.IUserService;
import cn.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    IUserService userService = new UserServiceImpl();
    ResultInfo resultInfo;
    ObjectMapper mapper;

    /**
     * 注册用户
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //验证图形验证码
            checkCheckCode(request, response);
            //封装对象
            Map<String, String[]> map = request.getParameterMap();
            User user = new User();
            BeanUtils.populate(user, map);
            int i = userService.registerUser(user);
            if (i == 1) {
                resultInfo = new ResultInfo("0002", "该用户名已被占用", null);
                throw new Exception("该用户名已被占用");
            }
            if (i == 2) {
                resultInfo = new ResultInfo("0003", "注册失败,请稍后重试", null);
                throw new Exception("注册失败,请稍后重试");
            }
            resultInfo = new ResultInfo("0000", "", null);
        } catch (Exception e) {

        } finally {
            //格式化返回数据
            writeValueAsString(resultInfo, response);
        }
    }

    /**
     * 激活用户
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void activate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        int i = userService.activateUser(code);
        if (i == 1) {
            response.setContentType("text/html;charset=utf-8");
            response.sendRedirect(request.getContextPath() + "/activate.html");
        } else {
            resultInfo = new ResultInfo("0000", "", null);
            writeValue(resultInfo, response);
        }
    }

    /**
     * 登录
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //验证图形验证码
            checkCheckCode(request, response);
            //封装User对象
            User user = new User();
            Map<String, String[]> map = request.getParameterMap();
            BeanUtils.populate(user, map);
            User session_user = userService.login(user);
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
            writeValue(resultInfo, response);
        }
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void signOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("session_user");
        response.setContentType("text/html;charset=utf-8");
        response.sendRedirect(request.getContextPath() + "/login.html");
    }

    /**
     * 用户session
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void userSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User session_user = (User) request.getSession().getAttribute("session_user");
        writeValueAsString(session_user, response);
    }

    /**
     * 验证图形验证码
     *
     * @param request
     * @param response
     * @throws Exception
     */
    public void checkCheckCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
    }
}
