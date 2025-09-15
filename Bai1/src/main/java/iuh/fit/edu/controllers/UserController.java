/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package iuh.fit.edu.controllers;

import iuh.fit.edu.daos.UserDao;
import iuh.fit.edu.daos.impl.UserDaoImpl;
import iuh.fit.edu.entities.Gender;
import iuh.fit.edu.entities.User;
import iuh.fit.edu.utils.EntityManagerFactoryUtil;
import jakarta.persistence.EntityManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */
@WebServlet(name = "userController", urlPatterns = {"/users", "/users*"})
public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action = "list";
        }
        if(action.equals("list")){
            handleShowListUser(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "insert":
                handleAddUser(req, resp);
                break;
        }
    }
    private void handleAddUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try(EntityManager entityManager = EntityManagerFactoryUtil.getEntityManager()) {
            UserDao userDao = new UserDaoImpl(entityManager);
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String name = firstName + " " + lastName;
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            Gender gender = request.getParameter("gender").equals("Female")
                    ? Gender.FEMALE : Gender.MALE;
            int day = Integer.parseInt(request.getParameter("dobDay"));
            int month = convertMonthToNumber(request.getParameter("dobMonth"));
            int year = Integer.parseInt(request.getParameter("dobYear"));
            LocalDate date = LocalDate.of(year, month, day);
            User newUser = new User(name, email, password, date, gender);
            User user = userDao.save(newUser);
            if(user != null){
                response.sendRedirect(request.getContextPath() + "/users?action=list");
            }

        }
    }
    public void handleShowListUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try(EntityManager entityManager = EntityManagerFactoryUtil.getEntityManager()){
            UserDao userDao = new UserDaoImpl(entityManager);
            List<User> users = userDao.findAll();
            request.setAttribute("users", users);
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/list.jsp");
            dispatcher.forward(request, response);
        }
    }
    private int convertMonthToNumber(String month) {
        switch (month) {
            case "J an":
                return 1;
            case "Feb":
                return 2;
            case "Mar":
                return 3;
            case "Apr":
                return 4;
            case "May":
                return 5;
            case "Jun":
                return 6;
            case "Jul":
                return 7;
            case "Aug":
                return 8;
            case "Sep":
                return 9;
            case "Oct":
                return 10;
            case "Nov":
                return 11;
            case "Dec":
                return 12;
            default:
                return 1;

        }
    }
}
