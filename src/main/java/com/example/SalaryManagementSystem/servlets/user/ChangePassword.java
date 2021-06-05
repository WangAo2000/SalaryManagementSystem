package com.example.SalaryManagementSystem.servlets.user;

import com.example.SalaryManagementSystem.bean.User;
import com.example.SalaryManagementSystem.dao.AdminDaoImpl;
import com.example.SalaryManagementSystem.dao.UserDaoImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ChangePassword", value = "/changePassword")
public class ChangePassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=GB2312");
        ServletContext context = this.getServletContext();
        String userId = (String) context.getAttribute("userid");
        String msg = (String) context.getAttribute("msg");
        if (msg == null){
            msg = "";
        }else if (!msg.equals("原密码不对")){
            msg = "";
        }
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>");

        out.println("<form action=\"/changePassword\" method=\"post\">\n" +
                "    <p><input type=\"text\" name=\"password\" placeholder=\"请输入原密码\"></p>\n" +
                "    <p><input type=\"text\" name=\"newPassword\" placeholder=\"请输入新密码\"></p>\n" +
                "    <p>"+ msg+"</p>\n" +
                "    <pre><input type=\"submit\" value=\"修改\">     <a href=\"/userDetails\">返回</a></pre>\n" +
                "</form>");
        out.println("</body>\n" +
                "</html>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = this.getServletContext();
        String userId = (String) context.getAttribute("userid");
        String password = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");
        AdminDaoImpl adminDao = new AdminDaoImpl();
        User user = adminDao.queryUser(userId);
        UserDaoImpl userDao = new UserDaoImpl();

        if (password !=null && newPassword != null && newPassword != ""){
            if (user.getPassword().equals(password)){
                userDao.updatePassword(userId, newPassword);
                System.out.println("修改成功");
                response.sendRedirect("/login");
            }else {
                context.setAttribute("msg","原密码不对");
                response.sendRedirect("/changePassword");
            }
        }else {
            response.sendRedirect("/changePassword");
        }


    }
}
