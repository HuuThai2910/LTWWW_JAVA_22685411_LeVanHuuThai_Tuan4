/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package iuh.fit.edu.controllers;

import iuh.fit.edu.daos.ProductDAO;
import iuh.fit.edu.daos.impl.ProductDAOImpl;
import iuh.fit.edu.entitites.ItemCart;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */
@WebServlet(name = "cartController", urlPatterns = {"/cart", "/cart*"})
public class CartController extends HttpServlet {
    @Resource(name = "jdbc/shopping")
    private DataSource dataSource;
    private ProductDAO productDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.productDAO = new ProductDAOImpl(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                handleAddToCart(req, resp);
                break;
            case "remove":
                handleRemoveFromCart(req, resp);
                break;
            case "show":
                handleShowCart(req, resp);
                break;
            default:
                handleUpdateToCart(req, resp);
                break;


        }
    }

    public void handleShowCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/cart/list.jsp").forward(req, resp);
    }

    public void handleAddToCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        List<ItemCart> cartList = null;
        if(session.getAttribute("cart") == null){
            cartList = new ArrayList<>();
        }else {
            cartList = (List<ItemCart>) session.getAttribute("cart");
        }
        int id = Integer.parseInt(req.getParameter("id"));
        int index = findProductIndex(id, cartList);
        if(index == -1){
            ItemCart newItemCart = new ItemCart();
            newItemCart.setProduct(productDAO.getProductById(id));
            newItemCart.setQuantity(1);
            cartList.add(newItemCart);
        }else {
            int quantity = cartList.get(index).getQuantity() + 1;
            cartList.get(index).setQuantity(quantity);
        }
        session.setAttribute("cart", cartList);
        resp.sendRedirect(req.getContextPath() + "/cart?action=show");
//        resp.setContentType("text/html;charset=UTF-8");
//        resp.getWriter().println("<script type='text/javascript'>");
//        resp.getWriter().println("alert('Product added to cart successfully!');");
//        resp.getWriter().println("window.history.back();");
//        resp.getWriter().println("</script>");
    }
    public void handleUpdateToCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        List<ItemCart> cartList = (List<ItemCart>) session.getAttribute("cart");
        int id = Integer.parseInt(req.getParameter("id"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        for (ItemCart item : cartList) {
            if (item.getProduct().getId() == id) {
                item.setQuantity(quantity);
                break;
            }
        }
        session.setAttribute("cart", cartList);
        resp.sendRedirect(req.getContextPath() + "/cart?action=show");
    }

    public void handleRemoveFromCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        List<ItemCart> cartList = (List<ItemCart>) session.getAttribute("cart");
        int index = findProductIndex(Integer.parseInt(req.getParameter("id")), cartList);
        cartList.remove(index);
        session.setAttribute("cart", cartList);
        resp.sendRedirect(req.getContextPath() + "/views/cart/list.jsp");

    }
    private int findProductIndex(int id, List<ItemCart> cartList){
        ItemCart existingItemCart = cartList.stream()
                .filter(itemCart -> itemCart.getProduct().getId() == id)
                .findFirst()
                .orElse(null);
        return existingItemCart != null ? cartList.indexOf(existingItemCart) : -1;
    }

}
