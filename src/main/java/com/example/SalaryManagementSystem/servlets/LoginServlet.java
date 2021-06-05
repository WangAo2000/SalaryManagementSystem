package com.example.SalaryManagementSystem.servlets;

import com.example.SalaryManagementSystem.bean.User;
import com.example.SalaryManagementSystem.dao.AdminDaoImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=GB2312");

        // 提示参数
        ServletContext context = this.getServletContext();
        String msg = (String) context.getAttribute("msg");
        String userid = (String) context.getAttribute("userid");
        if (msg == null) {
            msg = "";
        }
        if (userid == null){
            userid = "";
        }

        // 页面
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "    <style>\n" +
                "        #title {\n" +
                "            font-family: 宋体, serif;\n" +
                "            font-size: 50px;\n" +
                "            text-align: center;\n" +
                "            margin-top: 12.5%;\n" +
                "        }\n" +
                "        form {\n" +
                "            border: black solid 1px;\n" +
                "            border-radius: 10px;\n" +
                "            text-align: center;\n" +
                "            margin-left: 35%;\n" +
                "            padding: 1%;\n" +
                "            width: 25%;\n" +
                "            height: 140px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1 id=\"title\">登录</h1>\n" +
                "<form action=\"/login\" method=\"post\">\n" +
                "    <p style=\"margin: 0\">" + msg + "</p>\n" +
                "    <p>账号：<input type=\"text\" name=\"userid\" placeholder=\"请输入账号\" value=\""+userid+"\"></p>\n" +
                "    <p>密码：<input type=\"password\" name=\"password\" placeholder=\"请输入密码\"></p>\n" +
                "    <pre>   <input type=\"submit\" value=\"登录\"></pre>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        request.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("GBK");
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");

        // 查询user
        AdminDaoImpl adminDao = new AdminDaoImpl();
        User user = adminDao.queryUser(userid);
        String userId = user.getUserId();
        String userPassword = user.getPassword();
        ServletContext context = this.getServletContext();

        // 判断登录 两种情况：1.admin登录 2.密码不对
        if (userId!=null) {
            context.setAttribute("userid", userid);
            if (userId.equals("admin")){
                if (password.equals("123456")) {
                    response.sendRedirect("/adminDetails");
                } else {
                    context.setAttribute("msg", "密码错误");
                    response.sendRedirect("/login");
                }
            }else if (password.equals(userPassword)) {
                response.sendRedirect("/userDetails");
            } else {
                context.setAttribute("msg", "密码错误");
                response.sendRedirect("/login");
            }
        } else {
            context.setAttribute("msg", "用户不存在");
            response.sendRedirect("/login");
        }

    }
}
