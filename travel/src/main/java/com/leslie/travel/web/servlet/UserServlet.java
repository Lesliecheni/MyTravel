package com.leslie.travel.web.servlet;

import com.leslie.travel.domain.ResultInfo;
import com.leslie.travel.domain.User;
import com.leslie.travel.service.UserService;
import com.leslie.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @Author LeslieCheni
 * @Date Created in 13:59 2020/5/22 0022
 * @Version JDK1.8
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    private UserService service = new UserServiceImpl();

    /**
     * 注册功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //检验验证码
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");    //为了保证验证码只使用一次

        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)){

            ResultInfo resultInfo = new ResultInfo();
            //注册失败
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误,请点击验证码进行刷新并重新输入验证码");

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(resultInfo);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }


        //获取表单提交的数据
        Map<String, String[]> map = request.getParameterMap();

        User user = new User();

        //封装到user对象里
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        //调用service层进行注册功能
        //UserService service = new UserServiceImpl();
        boolean flag = service.regist(user);
        ResultInfo resultInfo = new ResultInfo();

        if (flag){
            //注册成功
            resultInfo.setFlag(true);
        }else {
            //注册失败
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("注册失败");

        }

        //将结果写为json对象，使用的jackson工具
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(resultInfo);

        String json = writeValueAsString(resultInfo);

//
//        //返回给视图层的数据为json格式
        response.setContentType("application/json;charset=utf-8");

        response.getWriter().write(json);

    }

    /**
     * 登录功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断验证码
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");    //为了保证验证码只使用一次

        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)){

            ResultInfo resultInfo = new ResultInfo();
            //注册失败
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误,请点击验证码进行刷新并重新输入验证码");

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(resultInfo);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }


        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //UserService service = new UserServiceImpl();
        User u = service.login(user);
        ResultInfo info = new ResultInfo();

        //判断用户对象u是否为null
        if (u == null){
            //用户名密码错误
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");

        }

        if (u!=null && !"Y".equals(u.getStatus())){
            //用户尚未激活
            info.setFlag(false);
            info.setErrorMsg("您尚未激活，请登录邮箱激活");
        }

        if (u!=null && "Y".equals(u.getStatus())){
            request.getSession().setAttribute("user",u);//登录成功标记
            info.setFlag(true);

        }

//
//        ObjectMapper mapper = new ObjectMapper();
//        response.setContentType("application/json;charset=utf-8");
//        mapper.writeValue(response.getOutputStream(),info);
        writeValue(info,response);
    }


    /**
     * 查询单个对象
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Object user = request.getSession().getAttribute("user");

//        ObjectMapper mapper = new ObjectMapper();
//        response.setContentType("application/json;charset=utf-8");
//        mapper.writeValue(response.getOutputStream(),user);

        writeValue(user,response);
    }


    /**
     * 退出功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().invalidate();//销毁session

        response.sendRedirect(request.getContextPath()+"/login.html");
    }


    /**
     * 激活用户
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        if (code!=null){
            //UserService service = new UserServiceImpl();
            boolean flag = service.active(code);


            String msg = null;
            if (flag){
                msg = "激活成功,请登录<a href='../login.html'>登录</a>";

            }else {
                msg = "激活失败,请联系管理员";
            }
            response.setContentType("text/html;charset=utf-8");

            response.getWriter().write(msg);

        }


    }



}
