package com.leslie.travel.web.servlet;

import com.leslie.travel.domain.Category;
import com.leslie.travel.service.CategoryService;
import com.leslie.travel.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author LeslieCheni
 * @Date Created in 18:12 2020/5/22 0022
 * @Version JDK1.8
 */
@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {

    private CategoryService service = new CategoryServiceImpl();


    /**
     * 查询所有
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Category> cs = service.findAll();
        writeValue(cs,response);

    }


}
