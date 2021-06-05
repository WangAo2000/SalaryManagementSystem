package com.example.SalaryManagementSystem.servlets.admin;

import com.example.SalaryManagementSystem.dao.AdminDaoImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DelUser", value = "/delUser")
public class DelUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=GB2312");
        AdminDaoImpl adminDao = new AdminDaoImpl();
        String userId = request.getParameter("userId");
        adminDao.delUser(userId);
        response.sendRedirect("/adminDetails");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
