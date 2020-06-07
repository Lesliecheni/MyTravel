package com.leslie.travel.web.servlet;

import com.leslie.travel.domain.PageBean;
import com.leslie.travel.domain.Route;
import com.leslie.travel.service.RouteService;
import com.leslie.travel.service.impl.RouServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author LeslieCheni
 * @Date Created in 8:59 2020/5/23 0023
 * @Version JDK1.8
 */
@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    private RouteService routeService = new RouServiceImpl();

    /**
     * 分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");

        String rname = request.getParameter("rname");
        System.out.println("servlet-->"+rname);

        //rname = new String(rname.getBytes("iso-8859-1"),"utf-8");


        int cid = 0;
        if (cidStr!=null && cidStr.length()>0 && !"null".equals(cidStr)){
            cid = Integer.parseInt(cidStr);

        }

        int currentPage = 0;
        if (currentPageStr!=null && currentPageStr.length()>0){
            currentPage = Integer.parseInt(currentPageStr);
        }else {
            currentPage = 1;
        }

        int pageSize = 0;
        if (pageSizeStr!=null && pageSizeStr.length()>0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else {
            pageSize = 5;
        }


        PageBean<Route> pb = routeService.pageQuery(cid, currentPage, pageSize,rname);
        //System.out.println(pb);
        writeValue(pb,response);


    }


    /**
     * 根据id查询一个l旅游线路的详情id
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String rid = request.getParameter("rid");
        Route route = routeService.findOne(rid);
        writeValue(route,response);

    }




}
