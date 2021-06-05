package com.example.SalaryManagementSystem.servlets.admin;

import com.example.SalaryManagementSystem.bean.User;
import com.example.SalaryManagementSystem.dao.AdminDaoImpl;
import com.example.SalaryManagementSystem.dao.UserDaoImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "UpdateUser", value = "/updateUser")
public class UpdateUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        ServletContext context = getServletContext();
        String userId = request.getParameter("userId");
        context.setAttribute("userId",userId);
        AdminDaoImpl adminDao = new AdminDaoImpl();
        User user = adminDao.queryUser(userId);

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>");
        out.println("<form action=\"/updateUser\" method=\"post\">\n" +
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
                "            <td><input type=\"text\" size=\"5\" name=\"userId\" value=\""+user.getUserId()+"\" disabled></td>\n" +
                "            <td><input type=\"text\" size=\"5\" name=\"name\" value=\""+user.getName()+"\"></td>\n" +
                "            <td><input type=\"text\" size=\"5\" name=\"password\" value=\""+user.getPassword()+"\"></td>\n" +
                "            <td><input type=\"text\" size=\"5\" name=\"dId\" value=\""+user.getdId()+"\"></td>\n" +
                "            <td><input type=\"text\" size=\"5\" name=\"profession\" value=\""+user.getProfession()+"\"></td>\n" +
                "            <td><input type=\"text\" size=\"5\" name=\"idNumber\" value=\""+user.getIdNumber()+"\"></td>\n" +
                "            <td><input type=\"text\" size=\"5\" name=\"bankId\" value=\""+user.getBankId()+"\"></td>\n" +
                "            <td><input type=\"submit\" value=\"修改\"></td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "</form>");


        out.println("</body>\n" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = this.getServletContext();
        String userId = (String) context.getAttribute("userId");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String dId = request.getParameter("dId");
        String profession = request.getParameter("profession");
        String idNumber = request.getParameter("idNumber");
        String bankId = request.getParameter("bankId");
        AdminDaoImpl adminDao = new AdminDaoImpl();
        System.out.println(name);

        adminDao.updateUser(userId,name,password,dId,profession,idNumber,bankId);
        response.sendRedirect("/adminDetails");
    }
}
