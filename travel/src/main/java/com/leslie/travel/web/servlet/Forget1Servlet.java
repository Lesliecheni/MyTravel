package com.leslie.travel.web.servlet;

import com.leslie.travel.domain.User;
import com.leslie.travel.util.JDBCUtils;
import com.leslie.travel.util.VerifyCodeUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/forget1")
public class Forget1Servlet extends HttpServlet {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("输入的验证码:"+request.getParameter("code"));
        System.out.println(("正确的验证码:"+VerifyCodeUtil.code));

        //验证码是否正确
        if(request.getParameter("code").equals(VerifyCodeUtil.code)) {
            //获取输入的密码
            String newpassword = (String)request.getParameter("password");
            //修改密码
            System.out.println("username:"+VerifyCodeUtil.username);
            System.out.println("newpassword:"+newpassword);
            try {
                //String sql = "update tab_user set password=" + newpassword + " where username=" + VerifyCodeUtil.username;
                String sql = "update tab_user set password= ? where username= ?";
                System.out.println("sql:" + sql);
                System.out.println("正在修改密码...");
                int i = template.update(sql,newpassword, VerifyCodeUtil.username);
                System.out.println(i);
                System.out.println("密码修改成功！");
                response.sendRedirect(request.getContextPath() + "/forget_ok.html");
            }catch (Exception e){
                System.out.println(e);
                System.out.println("error：密码修改失败");
            }
        }else{
            //验证码错误
            System.out.println("验证码错误");
            response.sendRedirect(request.getContextPath()+"/forget1.html");
        }
    }

}
