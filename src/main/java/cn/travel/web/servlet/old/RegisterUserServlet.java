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

@WebServlet("/registerUser")
public class RegisterUserServlet extends HttpServlet {
    IUserService userService = new UserServiceImpl();
    ResultInfo resultInfo = null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            //验证图形验证码
            String session_checkCode = (String) request.getSession().getAttribute("session_checkCode");
            String checkCode = request.getParameter("checkCode");

            if (!session_checkCode.equalsIgnoreCase(checkCode)) {
                resultInfo = new ResultInfo("0001", "验证码错误", null);
                throw new Exception("验证码错误");
            }

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
            ObjectMapper mapper = new ObjectMapper();
            String data = mapper.writeValueAsString(resultInfo);
            response.getWriter().write(data);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
