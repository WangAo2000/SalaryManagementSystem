package com.example.SalaryManagementSystem.servlets.user;

import com.example.SalaryManagementSystem.bean.User;
import com.example.SalaryManagementSystem.dao.AdminDaoImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserDetails", value = "/userDetails")
public class UserDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=GB2312");
        ServletContext context = this.getServletContext();
        String userId = (String) context.getAttribute("userid");
        context.setAttribute("userid",userId);
        AdminDaoImpl adminDao = new AdminDaoImpl();
        User user = adminDao.queryUser(userId);
        String name = user.getName();

        PrintWriter out = response.getWriter();
        out.println("<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>"
        );

        out.println("姓名："+name);
        out.println("<pre><a href=\"/checkSalary\">查看工资单</a>       <a href=\"/changePassword\">修改密码</a></pre>");
        out.println("</body>\n" +
                "</html>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
