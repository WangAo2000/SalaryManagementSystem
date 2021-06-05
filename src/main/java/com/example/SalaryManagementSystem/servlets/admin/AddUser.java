package com.example.SalaryManagementSystem.servlets.admin;

import com.example.SalaryManagementSystem.bean.User;
import com.example.SalaryManagementSystem.dao.AdminDaoImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddUser", value = "/addUser")
public class AddUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=GB2312");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>");
        out.println("<form action=\"/addUser\" method=\"post\">\n" +
                "    <table border=\"1\">\n" +
                "        <tr>\n" +
                "            <th>教职工号</th>\n" +
                "            <th>姓名</th>\n" +
                "            <th>密码</th>\n" +
                "            <th>部门编号</th>\n" +
                "            <th>职务</th>\n" +
                "            <th>身份证号</th>\n" +
                "            <th>银行卡号</th>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td><input type=\"text\" size=\"5\" name=\"userId\"></td>\n" +
                "            <td><input type=\"text\" size=\"5\" name=\"name\"></td>\n" +
                "            <td><input type=\"text\" size=\"5\" name=\"password\"></td>\n" +
                "            <td><input type=\"text\" size=\"5\" name=\"dId\"></td>\n" +
                "            <td><input type=\"text\" size=\"5\" name=\"profession\"></td>\n" +
                "            <td><input type=\"text\" size=\"5\" name=\"idNumber\"></td>\n" +
                "            <td><input type=\"text\" size=\"5\" name=\"bankId\"></td>\n" +
                "            <td><input type=\"submit\" value=\"添加\"></td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "</form>");
        out.println("</body>\n" +
                "</html>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userId = request.getParameter("userId");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String dId = request.getParameter("dId");
        String profession = request.getParameter("profession");
        String idNumber = request.getParameter("idNumber");
        String bankId = request.getParameter("bankId");

        AdminDaoImpl adminDao = new AdminDaoImpl();
        if (!userId.equals("")) {
            User user = adminDao.queryUser(userId);
            if (user.getUserId() == null) {
                adminDao.addUser(userId, name, password, dId, profession, idNumber, bankId);
                response.sendRedirect("/adminDetails");
            } else {
                response.sendRedirect("/addUser");
            }
        }else {
            response.sendRedirect("/addUser");
        }
    }
}
