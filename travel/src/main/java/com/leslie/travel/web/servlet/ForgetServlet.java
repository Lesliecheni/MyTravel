package com.leslie.travel.web.servlet;

import com.leslie.travel.domain.Category;
import com.leslie.travel.util.JDBCUtils;
import com.leslie.travel.util.MailUtil;
import com.leslie.travel.util.VerifyCodeUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/forget")
public class ForgetServlet extends HttpServlet {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String)request.getParameter("username");
        System.out.println("username:"+username);
        //从数据库查到的邮箱
        String sql = "select email from tab_user where username= ?";
        String email = (String) template.queryForMap(sql,username).get("email");
        System.out.println("email:"+email);

        //是否查到邮箱
        if(email.equals("null")){
            response.sendRedirect(request.getContextPath()+"/forget.html");
        }else{
            response.sendRedirect(request.getContextPath()+"/forget1.html");
            //生成验证码
            String code = VerifyCodeUtil.generateVerifyCode(4);
            System.out.println("code:"+code);
            //向邮箱发送验证码
            MailUtil mailthread = new MailUtil(email,code);
            mailthread.start();

            //保存验证码
            VerifyCodeUtil.code = code;
            //保存用户名
            VerifyCodeUtil.username = username;


        }
    }
}
