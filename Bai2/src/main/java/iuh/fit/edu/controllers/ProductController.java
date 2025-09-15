/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package iuh.fit.edu.controllers;

import iuh.fit.edu.daos.ProductDAO;
import iuh.fit.edu.daos.impl.ProductDAOImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;

/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */
@WebServlet(name = "productController", urlPatterns = "/products")
public class ProductController extends HttpServlet {
    @Resource(name = "jdbc/shopping")
    private DataSource dataSource;

    private ProductDAO productDAO;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.productDAO = new ProductDAOImpl(this.dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("products", productDAO.findAll());
        req.getRequestDispatcher("views/product/list.jsp").forward(req, resp);
    }
}
