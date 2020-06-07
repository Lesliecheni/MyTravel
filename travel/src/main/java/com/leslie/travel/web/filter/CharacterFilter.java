package com.leslie.travel.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author LeslieCheni
 * @Date Created in 15:48 2020/5/21 0021
 * @Version JDK1.8
 */
@WebFilter("/*")
public class CharacterFilter implements Filter {
    public void destroy() {
    }

    //设置统一的编码格式
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String method = request.getMethod();

        if (method.equalsIgnoreCase("post")){
            request.setCharacterEncoding("utf-8");
        }

        response.setContentType("text/html;charset=utf-8");

        chain.doFilter(request,response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
